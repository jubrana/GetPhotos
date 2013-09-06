package com.example.getphotos;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class PhotosAdapter extends ArrayAdapter<Photo> {

	private final Activity context;
	private List<Photo> photos;

	static class ViewHolder {
		public TextView title;
		public TextView author;
		public ImageView thumb;
		public String photoUrl;
		
	}

	public PhotosAdapter(Activity context, List<Photo> photos) {
        super(context, R.layout.row_layout, photos);
		this.context = context;
		this.photos = photos;
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.row_layout, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.title = (TextView) rowView
					.findViewById(R.id.title);
			viewHolder.author = (TextView) rowView
					.findViewById(R.id.author);
			viewHolder.thumb = (ImageView) rowView
					.findViewById(R.id.thumb);
			
			rowView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();
		Photo photo = photos.get(position);
		
		holder.title.setText(photo.getTitle());
		holder.author.setText(photo.getAuthor());
		holder.photoUrl = photo.getUrl();
		
		String str = (String) photo.getUrl();
		
		UrlImageViewHelper.setUrlDrawable(holder.thumb,
				str, R.drawable.ic_launcher);

		return rowView;
	}
	
	@Override
	public int getCount() {
		
		return photos.size();
	}
}
