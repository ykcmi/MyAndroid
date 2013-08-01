package com.example.imagefilter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.tools.FileUtils;
import com.example.tools.ImageUtils;

public class MainActivity extends Activity implements OnClickListener {

	private String mSdcardRootDir = null;
	private Button mCaptureImageBtn = null;
	private Button mSelectAlbumImageBtn = null;
	private String mFileName = null;
	
	private int CAMERA_CAPTURE_WITH_NAME = 0x01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSdcardRootDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		mCaptureImageBtn = (Button) this.findViewById(R.id.capture_image_btn);
		mSelectAlbumImageBtn = (Button) this
				.findViewById(R.id.select_album_image_btn);
		
		mCaptureImageBtn.setText("Capture");
		mSelectAlbumImageBtn.setText("Album");
		
		mCaptureImageBtn.setOnClickListener(this);
		mSelectAlbumImageBtn.setOnClickListener(this);
	}
	
	class MyHandler extends Handler{
		public MyHandler(MainActivity activity){
			
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.capture_image_btn:
			doCaptureImage();
			break;

		case R.id.select_album_image_btn:
			doSelectAlbumImage();
			break;
		}
	}

	private void doCaptureImage() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "no sdcard", Toast.LENGTH_SHORT).show();
			return;
		}

		long dateToken = System.currentTimeMillis();
		mFileName = mSdcardRootDir + "/DCIM/Camera/ImageFilter/";
		if(!FileUtils.mkdirs(mFileName)){
			return;
		}

		mFileName = mFileName + String.format("%d", dateToken) + ".jpg";
		
//		File file = new File(mFileName);
//		Uri photoUri = Uri.fromFile(file);
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		startActivityForResult(intent, CAMERA_CAPTURE_WITH_NAME);
	}

	private void doSelectAlbumImage() {

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != RESULT_OK){
			return;
//			if(requestCode == CAMERA_CAPTURE_WITH_NAME){
//				return;
//			}
		}
		
		if(requestCode == CAMERA_CAPTURE_WITH_NAME){
			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap)bundle.get("data");
			if(bitmap == null)
				return;
			
			ImageUtils.saveBitmapToJpg(bitmap, mFileName);
			loadImage();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void loadImage(){
		
	}
}
