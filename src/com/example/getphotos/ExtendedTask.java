package com.example.getphotos;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Objekti klase ExtendedTask kroz execute(ServiceParser... parsers) metodu primaju sve parsere kako bi se lista fotografija
 * jednostavno nadopunila fotografijama sa bilo kojih servisa.
 * 
 * @author Juraj Brana
 *
 */
public class ExtendedTask extends AsyncTask<ServiceParser, Void, List<Photo>> {

	private Activity activity;
	private ProgressDialog progress;

	public ExtendedTask(Activity activity) {

		this.activity = activity;
		this.progress = new ProgressDialog(activity);
	}

	@Override
	public void onPreExecute() {

		progress.setMessage(this.activity.getString(R.string.loading));
		progress.setIndeterminate(true);
		progress.setCancelable(false);
		progress.show();

	}

	protected List<Photo> doInBackground(ServiceParser... parsers) {

		for(ServiceParser parser : parsers) {
			
			parser.parseJSONResponse();
		}
		
		return Photos.getInstance().getPhotos();

	}

	@Override
	protected void onPostExecute(List<Photo> photos) {

		
		if (photos.size() == 0) {

			Toast.makeText(activity, "Connection problem try again", Toast.LENGTH_SHORT).show();
			
			activity.finish();
			return;
		}
		
		progress.dismiss();
		
		activity.setContentView(R.layout.layout);
		
		ListView photosList = (ListView) activity.findViewById(R.id.list_view);
		PhotosAdapter adapter = new PhotosAdapter(this.activity, photos);
		
		photosList.setAdapter(adapter);
		
	}
}
