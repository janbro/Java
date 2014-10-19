/*
 * Copyright (C) 2013 Keisuke SUZUKI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * Distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physicaloid.misc;

import android.util.Log;


public class RingBuffer{
    private static final String TAG = RingBuffer.class.getSimpleName();


    private int mRingBufSize;
    private byte[] mRingBuf;
    private int mAddIndex;     // top of data index
    private int mGetIndex;     // tail of data index

    /**
     * Ring buffer
     * @param bufferSize buffer size. It needs enough size e.g.1024
     */
    public RingBuffer(int bufferSize) {
        mRingBufSize = bufferSize+1;
        mRingBuf = new byte[mRingBufSize];
        mAddIndex = 0;
        mGetIndex = 0;
    }

    /**
     * Gets ring buffer size
     * @return ring buffer size
     */
    public int getRingBufferSize() {
        return mRingBufSize-1;
    }

    /**
     * Gets buffered length
     * @return buffered length
     */
    public int getBufferdLength() {
        if(mAddIndex >= mGetIndex) {
            return mAddIndex - mGetIndex;
        } else {
            return mAddIndex + (mRingBufSize - mGetIndex);
        }
    }

    /**
     * Adds byte array to ring buffer
     * @param buf byte array
     * @param length added length
     * @return actually added length
     */
    public synchronized int add(byte[] buf, int length) {
        int addLen = length;

        if(mAddIndex > mGetIndex) {
            if((mAddIndex + length) >= mRingBufSize) {                          // addした結果1周をまたぐ場合
                if((mRingBufSize - mAddIndex) + (mGetIndex - 1) < length ) {    // 1周をまたいでなおlength以上になる場合
                    addLen = (mRingBufSize - mAddIndex) + (mGetIndex-1);        // 追い抜かないサイズに修正
                }
            }
        } else if(mAddIndex < mGetIndex){ // 1周をまたいでいる場合
            if((mGetIndex - 1) - mAddIndex < length) {
                addLen = (mGetIndex - 1) - mAddIndex;
            }
        }

        if(buf.length < addLen) {
            addLen = buf.length;
        }

        if((mAddIndex+addLen) >= mRingBufSize) { // storeがバッファ終端をまたぐ場合
            int remain = mAddIndex + addLen - mRingBufSize;
            int copyLen = addLen-remain;
            if(copyLen != 0) {
                System.arraycopy(buf, 0, mRingBuf, mAddIndex, copyLen);
            }

            mAddIndex = 0;

            if(remain != 0) {
                System.arraycopy(buf, copyLen, mRingBuf, mAddIndex, remain);
                mAddIndex = remain;
            }


            return addLen;
        } else {
            System.arraycopy(buf, 0, mRingBuf, mAddIndex, addLen);


            mAddIndex += addLen;


            return addLen;
        }
    }

    /**
     * Gets ring buffer to byte array
     * @param buf byte array
     * @param length gotten length
     * @return actually gotten length
     */
    public synchronized int get(byte[] buf, int length) {
        int getLen = length;
        if(mAddIndex == mGetIndex) {
            return 0;
        } else if(mGetIndex < mAddIndex) { // 通常
            if(mAddIndex - mGetIndex < length) {  // get要求サイズがバッファされているサイズより大きい場合
                getLen = mAddIndex - mGetIndex;     // 今バッファされているサイズを返す
            }
        } else {// インデックスが1周をまたいでいる場合
            if(mAddIndex + (mRingBufSize-mGetIndex) < length) {     // get要求サイズがバッファされているサイズより大きい場合
                getLen = mAddIndex + (mRingBufSize - mGetIndex);    // 今バッファされているサイズを返す
            }
        }

        if(buf.length < getLen) {
            getLen = buf.length;
        }

        if((mGetIndex+getLen) >= mRingBufSize) {
            int remain = mGetIndex + getLen - mRingBufSize;
            int copyLen = getLen - remain;
            if( copyLen != 0) {
                System.arraycopy(mRingBuf, mGetIndex, buf, 0, copyLen);
            }

            mGetIndex = 0;

            if(remain !=0) {
                System.arraycopy(mRingBuf, mGetIndex, buf, copyLen, remain);
                mGetIndex = remain;
            }

            return getLen;
        } else {
            System.arraycopy(mRingBuf, mGetIndex, buf, 0, getLen);


            mGetIndex += getLen;


            return getLen;
        }
    }


    /**
     * Clear ring buffer
     */
    public synchronized void clear() {
        mAddIndex = 0;
        mGetIndex = 0;
    }

}
