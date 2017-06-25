package com.happyhours.mainmodule;
//code took from  http://www.androidinterview.com/android-custom-listview-with-image-and-text-using-arrayadapter/
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CustomListAdapter extends ArrayAdapter<String> {
 
	private final Activity context;
	private final String[] itemname;
	private final Integer[] imgid;
	private final String[] address;
	
	public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid,String[] address) {

		super(context, R.layout.mylist, itemname);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.itemname=itemname;
		this.imgid=imgid;
		this.address=address;
	}
	
	public View getView(int position,View view,ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.mylist, null,true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
		
		txtTitle.setText(itemname[position]);
		imageView.setImageResource(imgid[position]);
		extratxt.setText("Address: "+address[position]);
		return rowView;
		
	};
}