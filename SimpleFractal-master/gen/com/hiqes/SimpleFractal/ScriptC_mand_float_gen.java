/*
 * Copyright (C) 2011-2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is auto-generated. DO NOT MODIFY!
 * The source Renderscript file: C:\\Users\\debasish\\Documents\\Received Files\\SimpleFractal-master\\SimpleFractal-master\\src\\com\\hiqes\\SimpleFractal\\mand_float_gen.rs
 */

package com.hiqes.SimpleFractal;

import android.renderscript.*;
import android.content.res.Resources;

/**
 * @hide
 */
public class ScriptC_mand_float_gen extends ScriptC {
    private static final String __rs_resource_name = "mand_float_gen";
    // Constructor
    public  ScriptC_mand_float_gen(RenderScript rs) {
        this(rs,
             rs.getApplicationContext().getResources(),
             rs.getApplicationContext().getResources().getIdentifier(
                 __rs_resource_name, "raw",
                 rs.getApplicationContext().getPackageName()));
    }

    public  ScriptC_mand_float_gen(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id);
        __I32 = Element.I32(rs);
        __ALLOCATION = Element.ALLOCATION(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __ALLOCATION;
    private Element __I32;
    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_width = 0;
    private int mExportVar_width;
    public synchronized void set_width(int v) {
        setVar(mExportVarIdx_width, v);
        mExportVar_width = v;
    }

    public int get_width() {
        return mExportVar_width;
    }

    public Script.FieldID getFieldID_width() {
        return createFieldID(mExportVarIdx_width, null);
    }

    private final static int mExportVarIdx_height = 1;
    private int mExportVar_height;
    public synchronized void set_height(int v) {
        setVar(mExportVarIdx_height, v);
        mExportVar_height = v;
    }

    public int get_height() {
        return mExportVar_height;
    }

    public Script.FieldID getFieldID_height() {
        return createFieldID(mExportVarIdx_height, null);
    }

    private final static int mExportVarIdx_iter = 2;
    private int mExportVar_iter;
    public synchronized void set_iter(int v) {
        setVar(mExportVarIdx_iter, v);
        mExportVar_iter = v;
    }

    public int get_iter() {
        return mExportVar_iter;
    }

    public Script.FieldID getFieldID_iter() {
        return createFieldID(mExportVarIdx_iter, null);
    }

    private final static int mExportVarIdx_palette = 3;
    private int[] mExportVar_palette;
    public synchronized void set_palette(int[] v) {
        mExportVar_palette = v;
        FieldPacker fp = new FieldPacker(4100);
        for (int ct1 = 0; ct1 < 1025; ct1++) {
            fp.addI32(v[ct1]);
        }

        int []__dimArr = new int[1];
        __dimArr[0] = 1025;
        setVar(mExportVarIdx_palette, fp, __I32, __dimArr);
    }

    public int[] get_palette() {
        return mExportVar_palette;
    }

    public Script.FieldID getFieldID_palette() {
        return createFieldID(mExportVarIdx_palette, null);
    }

    private final static int mExportVarIdx_paletteLen = 4;
    private int mExportVar_paletteLen;
    public synchronized void set_paletteLen(int v) {
        setVar(mExportVarIdx_paletteLen, v);
        mExportVar_paletteLen = v;
    }

    public int get_paletteLen() {
        return mExportVar_paletteLen;
    }

    public Script.FieldID getFieldID_paletteLen() {
        return createFieldID(mExportVarIdx_paletteLen, null);
    }

    private final static int mExportVarIdx_zoom = 5;
    private int mExportVar_zoom;
    public synchronized void set_zoom(int v) {
        setVar(mExportVarIdx_zoom, v);
        mExportVar_zoom = v;
    }

    public int get_zoom() {
        return mExportVar_zoom;
    }

    public Script.FieldID getFieldID_zoom() {
        return createFieldID(mExportVarIdx_zoom, null);
    }

    private final static int mExportVarIdx_centerX = 6;
    private int mExportVar_centerX;
    public synchronized void set_centerX(int v) {
        setVar(mExportVarIdx_centerX, v);
        mExportVar_centerX = v;
    }

    public int get_centerX() {
        return mExportVar_centerX;
    }

    public Script.FieldID getFieldID_centerX() {
        return createFieldID(mExportVarIdx_centerX, null);
    }

    private final static int mExportVarIdx_centerY = 7;
    private int mExportVar_centerY;
    public synchronized void set_centerY(int v) {
        setVar(mExportVarIdx_centerY, v);
        mExportVar_centerY = v;
    }

    public int get_centerY() {
        return mExportVar_centerY;
    }

    public Script.FieldID getFieldID_centerY() {
        return createFieldID(mExportVarIdx_centerY, null);
    }

    private final static int mExportVarIdx_alloc_palette = 8;
    private Allocation mExportVar_alloc_palette;
    public synchronized void set_alloc_palette(Allocation v) {
        setVar(mExportVarIdx_alloc_palette, v);
        mExportVar_alloc_palette = v;
    }

    public Allocation get_alloc_palette() {
        return mExportVar_alloc_palette;
    }

    public Script.FieldID getFieldID_alloc_palette() {
        return createFieldID(mExportVarIdx_alloc_palette, null);
    }

    private final static int mExportForEachIdx_root = 0;
    public Script.KernelID getKernelID_root() {
        return createKernelID(mExportForEachIdx_root, 26, null, null);
    }

    public void forEach_root(Allocation aout) {
        forEach_root(aout, null);
    }

    public void forEach_root(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_root, (Allocation) null, aout, null, sc);
    }

    private final static int mExportFuncIdx_setPalette = 0;
    public void invoke_setPalette() {
        invoke(mExportFuncIdx_setPalette);
    }

}

