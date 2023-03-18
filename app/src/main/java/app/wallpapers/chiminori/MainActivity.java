package app.wallpapers.chiminori;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.*;
import com.github.wallpoo.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private boolean isDataLoaded = false;
	
	private ArrayList<HashMap<String, Object>> mapList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> shimmerList = new ArrayList<>();
	
	private RecyclerView recyclerview1;
	
	private RequestNetwork getData;
	private RequestNetwork.RequestListener _getData_request_listener;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		recyclerview1 = findViewById(R.id.recyclerview1);
		getData = new RequestNetwork(this);
		
		_getData_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				//Variables Names
				
				ArrayList<String> splitCsvList = new ArrayList<String>();
				ArrayList<String> splitComa = new ArrayList<String>();
				ArrayList<String> tableNameLists = new ArrayList<String>();
				HashMap<String, Object> mapVariable = new HashMap<>();
				ArrayList<HashMap<String, Object>> mapList = new ArrayList<>();
				
				splitCsvList.clear();
				splitComa.clear();
				tableNameLists.clear();
				mapList.clear();
				splitCsvList = new ArrayList<String>(Arrays.asList(_response.replace("\\r", "").split("\n")));
				for(int _repeat16 = 0; _repeat16 < (int)(splitCsvList.size()); _repeat16++) {
						splitComa = new ArrayList<String>(Arrays.asList(splitCsvList.get((int)(_repeat16)).split(",")));
						if (_repeat16 == 0) {
								tableNameLists.addAll(splitComa);
						}
						else {
								mapVariable = new HashMap<>();
								for(int _repeat26 = 0; _repeat26 < (int)(tableNameLists.size()); _repeat26++) {
							//Developed By App Helper (YouTube Channel)
							//This blocks is downloaded from App Helper Store App
										mapVariable.put(tableNameLists.get((int)(_repeat26)), splitComa.get((int)(_repeat26)));
								}
								mapList.add(mapVariable);
						}
						splitComa.clear();
				}
				mapList = new Gson().fromJson(new Gson().toJson(mapList).replace("\\r", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				isDataLoaded = true;
				recyclerview1.setAdapter(new Recyclerview1Adapter(mapList));
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		isDataLoaded = false;
		String SCRIPT_ID = "";
		String SHEET_NAME = "";
		String SHEET_NUMBER = ""; 
		String SPREADSHEET_ID = "1aEjkzb25qJs2rdl-Y8ZBk918ZjCJtZigfAXff2C9Too";
		getData.startRequestNetwork(RequestNetworkController.GET, "https://docs.google.com/spreadsheets/d/".concat(SPREADSHEET_ID.concat("/export?format=csv&gid=".concat("0"))), "getData", _getData_request_listener);
		for(int _repeat18 = 0; _repeat18 < (int)(20); _repeat18++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("shi", "shi");
				shimmerList.add(_item);
			}
			
		}
		recyclerview1.setAdapter(new Recyclerview1Adapter(shimmerList));
		GridLayoutManager gridlayoutManager= new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL,true); gridlayoutManager.setReverseLayout(false); recyclerview1.setLayoutManager(gridlayoutManager);
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.gridview, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout mainLayout = _view.findViewById(R.id.mainLayout);
			final com.facebook.shimmer.ShimmerFrameLayout shimmerLayout = _view.findViewById(R.id.shimmerLayout);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			if (isDataLoaded) {
				//if isDataLoaded = true
				shimmerLayout.setVisibility(View.GONE);
				mainLayout.setVisibility(View.VISIBLE);
				mainLayout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, Color.TRANSPARENT));
				mainLayout.setElevation((float)10);
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("Images").toString())).into(imageview1);
			}
			else {
				//if isdataLoaded = false
				shimmerLayout.setVisibility(View.VISIBLE);
				mainLayout.setVisibility(View.GONE);
			}
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getApplicationContext(), ViewActivity.class);
					i.putExtra("url", _data.get((int)_position).get("Images").toString());
					startActivity(i);
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
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