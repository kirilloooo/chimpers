package app.wallpapers.chiminori;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.*;
import com.github.wallpoo.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ViewActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private FloatingActionButton _fab;
	private double pos = 0;
	private String url = "";
	
	private LinearLayout linear1;
	private ImageView imageview1;
	
	private AlertDialog.Builder dialog1;
	private TimerTask t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		dialog1 = new AlertDialog.Builder(this);
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog1.setTitle("Set Wallpaper");
				String[] items = {"For home screen", "For lock screen", "For both"};
				int checkItem = 0;
				dialog1.setSingleChoiceItems(items, checkItem, new DialogInterface.OnClickListener() { 
					public void onClick(DialogInterface dialog, int which) { 
						switch (which) {
							case 0: 
							pos = 0;
							break;
							case 1: 
							pos = 1;
							break;
							case 2: 
							pos = 2;
							break;
							case 3: 
							pos = 3;
							break; 
						} 
					} });
				dialog1.setPositiveButton("Set", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (pos == 0) {
							Wallpo.setMainScreenWallpaper(ViewActivity.this, imageview1, "Wallpaper Set"); 		//imageview is the view where image is loading this code by omar boudiba
						}
						else {
							if (pos == 1) {
								Wallpo.setLockScreenWallpaper(ViewActivity.this, imageview1, "LockWallpaper Set"); 
								 				//imageview is the view where image is loading this code by omar boudiba
								
							}
							else {
								if (pos == 2) {
									Wallpo.setMainScreenWallpaper(ViewActivity.this, imageview1, "Wallpaper Set"); 		//imageview is the view where image is loading this code by omar boudiba
									Wallpo.setLockScreenWallpaper(ViewActivity.this, imageview1, "LockWallpaper Set"); 
									 				//imageview is the view where image is loading this code by omar boudiba
									
								}
							}
						}
					}
				});
				dialog1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog1.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
					    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
		}
		if (Build.VERSION.SDK_INT >= 19) {
					    getWindow().getDecorView().setSystemUiVisibility(
					            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					    );
		}
		if (Build.VERSION.SDK_INT >= 21) {
					    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
					    getWindow().setStatusBarColor(Color.TRANSPARENT);
					    getWindow().setNavigationBarColor(Color.TRANSPARENT);
		}
	}
	private void setWindowFlag(final int bits, boolean on) {
		    Window win = getWindow();
		    WindowManager.LayoutParams winParams = win.getAttributes();
		    if (on) {
					        winParams.flags |= bits;
					    } else {
					        winParams.flags &= ~bits;
					    }
		    win.setAttributes(winParams);
	}
	{
	}
	
	@Override
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						url = getIntent().getStringExtra("url");
						Glide.with(getApplicationContext()).load(Uri.parse(url)).into(imageview1);
					}
				});
			}
		};
		_timer.schedule(t, (int)(1000));
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}