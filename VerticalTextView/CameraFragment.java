
package com.test.cctv;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sds.mobile.servicebrokerLib.ServiceBrokerLib;
import com.sds.mobile.servicebrokerLib.event.ResponseEvent;
import com.sds.mobile.servicebrokerLib.event.ResponseListener;
import com.sem.apps.common.ServiceBrockerIntent;
import com.test.cctv.model.CustomCameraListAdapter;
import com.test.cctv.model.DataBean;
import com.test.login.MESPortalConstants;

/**
 * @file        : CameraFragment.java 
 * @description : Fragment containing list of cameras 
 * @author      : Debasish Pradhan 
 * @email       : debasish.pradhan@samsung.com
 * @project     : CCTV 
 * @moduleName  : GUI File 
 * @modifiedBy  : debasish.pradhan@samsung.com 
 * @creationDate: 08-Feb-2016
 * @modifiedDate: 08-Feb-2016
 */
public class CameraFragment extends BaseFragment {

	private Context context;
	private ListView cameraListView;
	private VerticalTextView corpNameTV,buildingTV,floorTV;
	private ProgressDialog mProgresDialog;
	private ArrayList<DataBean> mDatabeanList;
	private MESPortalConstants mesportal;
	private TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_camera, container, false);
		
		DataBean dataBean =(DataBean) getArguments().get("data");
		corpNameTV    = (VerticalTextView) view.findViewById(R.id.corpNameTV);
		buildingTV    = (VerticalTextView) view.findViewById(R.id.buildNameTV);
		floorTV       = (VerticalTextView) view.findViewById(R.id.floorNameTV);
		
		textView     = (TextView) view.findViewById(R.id.no_data_id);
		corpNameTV.setText(dataBean.getCorporation());
		buildingTV.setText(dataBean.getBuilding());
		floorTV.setText(dataBean.getFloorName());
		
		context      = this.getActivity();
		mesportal    = MESPortalConstants.getInstance();
		cameraListView = (ListView)  view.findViewById(R.id.cameraListView);
		
	    cameraListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long arg3) {
				DataBean dataBean = mDatabeanList.get(position);
				Toast.makeText(context, ""+dataBean.getCameraName(), Toast.LENGTH_SHORT).show();
				insertNestedFragment(dataBean);
				
			}
		});
	    
	    executeBackgroundService(dataBean);
		return view;
	}
	
	/**
	 * Embeds the child fragment dynamically
	 * 
	 * @param dataBean
	 *            : java bean to hold user selection details
	 */
	private void insertNestedFragment(DataBean dataBean) {

	}

	/**
	 * Shows the Progress dialog
	 * 
	 */
	private void showProgressDialog() {
		mProgresDialog = new ProgressDialog(context);
		mProgresDialog.setCancelable(false);
		mProgresDialog.setMessage(getResources().getString(R.string.PleaseWait));
		mProgresDialog.show();
	}

	/**
	 * calls background service to fetch data
	 * 
	 */
	private void executeBackgroundService(final DataBean previousBean) {

		ServiceBrokerLib lib = new ServiceBrokerLib(new ResponseListener() {
			@Override
			public void receive(ResponseEvent re) {

				if (ServiceBrockerIntent.SUCCEEDED(re.getResultCode())) {
					try {
						String s = re.getResultData();

						mDatabeanList = new ArrayList<DataBean>();
						if (!s.equals("")) {

							textView.setVisibility(View.GONE);
							JSONArray array = new JSONArray(s);

							for (int i = 0; i < array.length(); i++) {
								DataBean dataBean = new DataBean();
								dataBean.setCorporation(previousBean
										.getCorporation());
								dataBean.setCorpoRecId(previousBean
										.getCorpoRecId());
								dataBean.setBuilding(previousBean.getBuilding());
								dataBean.setBuildingRecId(previousBean
										.getBuildingRecId());
								dataBean.setFloorName(previousBean
										.getFloorName());
								dataBean.setFloorRecId(previousBean
										.getFloorRecId());

								String cameraName = array.getJSONObject(i)
										.getString("CameraName");
								dataBean.setCameraName(cameraName);

								String cameraAccessUrl = array.getJSONObject(i)
										.getString("AccessURL");
								dataBean.setCameraAccessUrl(cameraAccessUrl);

								String cameraShot = array.getJSONObject(i)
										.getString("Screenshot");
								dataBean.setCameraShot(cameraShot);

								String cameraRecId = array.getJSONObject(i)
										.getString("CameraRecID");
								dataBean.setCameraRecId(cameraRecId);

								String resolutionDetails = array.getJSONObject(
										i).getString("ResolutionDetails");
								dataBean.setResolutionDetails(resolutionDetails);

								String isUserBookmark = array.getJSONObject(i)
										.getString("IsUserBookMark");
								dataBean.setIsUserBookmark(isUserBookmark);

								mDatabeanList.add(dataBean);
							}

							updateLisView(mDatabeanList);

						} else {

							textView.setVisibility(View.VISIBLE);
							mProgresDialog.dismiss();
						}

					} catch (JSONException e) {
						mProgresDialog.dismiss();
						e.printStackTrace();
					}
				} else {
					Log.i("@SEMP@", "-@--" + re.getResultData());
					mProgresDialog.dismiss();
					Toast.makeText(
							context,
							getString(R.string.Unable_to_get_information_from_server),
							Toast.LENGTH_LONG).show();
				}
			}

		});

		showProgressDialog();
		ServiceBrockerIntent intent = new ServiceBrockerIntent(context,
				mesportal.getUserId());
		intent.putExtra("sType", "db");
		intent.putExtra("sCode", "DB_MESPORTAL_AGET_CCTV_CAMERA");
		intent.putExtra("parameter", "empno=" + mesportal.getEmpNumber() + "&"
				+ "floorrecid=" + previousBean.getFloorRecId());

		lib.request(intent);
	}

	/**
	 * @param cameraList
	 *            updates listview by passing arraylist
	 */
	private void updateLisView(ArrayList<DataBean> cameraList) {
		cameraListView.setAdapter(new CustomCameraListAdapter((Activity) context, cameraList));
		mProgresDialog.dismiss();
	}

}
