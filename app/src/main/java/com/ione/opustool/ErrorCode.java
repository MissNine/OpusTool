package com.ione.opustool;


import android.content.Context;
import android.content.res.Resources.NotFoundException;

public class ErrorCode {

	public final static int SUCCESS = 1000;
	public final static int E_NOSDCARD = 1001;
	public final static int E_STATE_RECODING = 1002;
	public final static int E_UNKOWN = 1003;

	public static String getErrorInfo(Context vContext, int vType) throws NotFoundException {
		switch (vType) {
		case SUCCESS:
			return "success";
		case E_NOSDCARD:
			return "";
		case E_STATE_RECODING:
			return "";
		case E_UNKOWN:
		default:
			return "未知";

		}
	}
}
