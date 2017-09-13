package com.ione.opustool;
/**
 * 压缩库
 *
 * @author lzhen
 *
 */
public class OpusJni
{
	private static OpusJni mInstance;

	public synchronized static OpusJni getInstance() {
		if (mInstance == null)
			mInstance = new OpusJni();
		return mInstance;
	}

	static
	{
		System.loadLibrary("newopus");
	}
	public native int Opusopen(int complexity);  //complexity:8
	public native int OpusgetFrameSize();
	public native int Opusencode(short[] src, int offset, byte[] out, int size); // 压缩数据，长度为320short->40byte
	public native int Opusdecode(byte[] src, short[] out, int size);// 解压缩数据，长度为40byte->320short
	public native void Opusclose(); // 释放内存
	public native void OpusEncode(String inpath, String outpath);
}