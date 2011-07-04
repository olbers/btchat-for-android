package com.fanfq.BTInterphone;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class BTInterphoneActivity extends Activity {
	
	
	private String iTempFileNameString = "iRecorder_";
	private File iRecAudioFile;
	private File iRecAudioDir;
	private File iPlayFile;
	private MediaRecorder iMediaRecorder;

	private ArrayList<String> iRecordFiles;
	private ArrayAdapter<String> iAdapter;
	private TextView iTextView;
	private boolean isSDCardExit;
	private boolean isStopRecord;
	
	
    /** Called when the activity is first created. */
	
	private final boolean D = true;
	private final String TAG = "BTInterphoneActivity";
	private Button chosebtn;
	private Button speakbtn;
	private Button choseSpeakBtn;
	private Button choseMsgBtn;
	private Button choseSmilyBtn;
	private Button choseImageBtn;
	
	PopupWindow pop;
	MediaRecorder mMediaRecorder;
	View view;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        /* �ж�SD Card�Ƿ���� */
		isSDCardExit = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		/* ȡ��SD Card·����Ϊ¼�����ļ�λ�� */
		if (isSDCardExit)
			iRecAudioDir = Environment.getExternalStorageDirectory();
        
        speakbtn = (Button) findViewById(R.id.speakbtn);
        speakbtn.setFocusable(true);
        speakbtn.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == event.ACTION_DOWN){
					speakbtn.setText("�ɿ�����");
					System.out.println("�ɿ�����");
					
				}
				if(event.getAction() == event.ACTION_UP){
					speakbtn.setText("��ס�Խ�");

				}	
				
				return false;
			}
		});
        
        chosebtn = (Button) findViewById(R.id.chosebtn);
        chosebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(pop.isShowing())
				{
					pop.dismiss();
				}
				else
				{ 
					pop.showAsDropDown(v, 0, -160); 
				}
				
			}
		});
        initPopupWindow();
    }
    
    private void initPopupWindow()
	{
		view = this.getLayoutInflater().inflate(R.layout.popup_window, null);
		pop = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		pop.setOutsideTouchable(true);
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		
		choseSpeakBtn = (Button) view.findViewById(R.id.choseSpeakBtn);
        choseSpeakBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("speak");
			}
		});
        
        choseMsgBtn = (Button) view.findViewById(R.id.choseMsgBtn);
        choseMsgBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
        
        choseSmilyBtn = (Button) view.findViewById(R.id.choseSmilyBtn);
        choseSmilyBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
        
        choseImageBtn = (Button) view.findViewById(R.id.choseImageBtn);
        choseImageBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
	}
    
    @Override
	protected void onStop() {
		if (iMediaRecorder != null && !isStopRecord) {
			/* ֹͣ¼�� */
			iMediaRecorder.stop();
			iMediaRecorder.release();
			iMediaRecorder = null;
		}
		super.onStop();
	}
    
}