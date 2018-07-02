/*
	Office Server Document Converter Java Interface sample program

	The following indicates the sample of formatting using render method.

	Copyright 2018 Antenna House, Inc.
*/


import java.io.*;
import jp.co.antenna.DfvJavaCtl.*;

class ErrDump implements MessageListener {

	public void onMessage(int errLevel, int errCode, String errMessage) {
		System.out.println("ErrorLevel = " + errLevel + "\nErrorCode = " + errCode + "\n" + errMessage);
	}
}

public class sample3 {
	public static void main(String args []) {
		if (args.length < 2) {
			System.out.println("usage: java sample office-file out-file");
			return;
		}

		DfvObj adfv = null;
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			adfv = new DfvObj();
			ErrDump eDump = new ErrDump();
			adfv.setMessageListener(eDump);
			adfv.setExitLevel(4);

			is = new FileInputStream(args[0]);
			os = new FileOutputStream(args[1]);
			adfv.render(is, os, "@PDF");
		}
		catch (DfvException e) {
			System.out.println("ErrorLevel = " + e.getErrorLevel() + "\nErrorCode = " + e.getErrorCode() + "\n" + e.getErrorMessage());
			return;
		}
	    catch (Exception e)
	    {
			e.printStackTrace();
			return;
		}
		finally
		{
			try {
				if (adfv != null)
					adfv.releaseObjectEx();
			}
			catch (DfvException e) {
				System.out.println("ErrorLevel = " + e.getErrorLevel() + "\nErrorCode = " + e.getErrorCode() + "\n" + e.getErrorMessage());
				return;
			}
			try {
				is.close();
			} catch (Exception e) {}
			try {
				os.close();
			} catch (Exception e) {}
		}
		System.out.println("Formatting finished: '" + args[1] + "' created.");
	}
}
