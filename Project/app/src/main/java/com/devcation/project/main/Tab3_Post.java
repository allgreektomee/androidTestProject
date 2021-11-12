package com.devcation.project.main;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.devcation.project.BuildConfig;
import com.devcation.project.R;
import com.devcation.project.post.PostDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;


public class Tab3_Post extends Fragment {
    Button btnInsert;
    EditText editTitle;
    EditText editContents;
    ImageView imgView;

    Uri uri;
    File file;
    Bitmap resultPhotoBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab3_post, container, false);
        editTitle = rootView.findViewById(R.id.editTitle);
        editContents = rootView.findViewById(R.id.editContents);

        btnInsert = rootView.findViewById(R.id.button2);
        btnInsert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                PostDatabase database = PostDatabase.getInstance(getContext());
                database.insertRecord(editTitle.getText().toString(), editContents.getText().toString(), savePicture());
            }
        });

        imgView = rootView.findViewById(R.id.imageView2);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    file = createFile();
                    if (file.exists()) {
                        file.delete();
                    }

                    file.createNewFile();
                } catch(Exception e) {
                    e.printStackTrace();
                }

                if(Build.VERSION.SDK_INT >= 24) {
                    uri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID, file);
                } else {
                    uri = Uri.fromFile(file);
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                startActivityForResult(intent, 1000);
            }
        });

        return rootView;
    }

    private File createFile() {
        Date curDate = new Date();
        String curDateStr = String.valueOf(curDate.getTime());

        String filename = curDateStr;
        File outFile = new File(getContext().getFilesDir(), filename);
        Log.d("Main", "File path : " + outFile.getAbsolutePath());

        return outFile;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (intent != null) {
            resultPhotoBitmap = decodeSampledBitmapFromResource(file, imgView.getWidth(), imgView.getHeight());
            imgView.setImageBitmap(resultPhotoBitmap);

            //




//            Uri fileUri = intent.getData();
//
//            ContentResolver resolver = getContext().getContentResolver();
//
//            try {
//                InputStream instream = resolver.openInputStream(fileUri);
//                resultPhotoBitmap = BitmapFactory.decodeStream(instream);
//                pictureImageView.setImageBitmap(resultPhotoBitmap);
//
//                instream.close();
//
//
//
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(File res, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(res.getAbsolutePath(),options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(res.getAbsolutePath(),options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height;
            final int halfWidth = width;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private String savePicture() {

        String folderPath = getActivity().getFilesDir().getAbsolutePath();
        String photoPath = folderPath + File.separator + "photo";

        File photoFolder = new File(photoPath);

        if(!photoFolder.isDirectory()) {
            photoFolder.mkdirs();
        }

        Date curDate = new Date();
        String curDateStr = String.valueOf(curDate.getTime());

        String photoFilename =curDateStr;
        String picturePath = photoFolder + File.separator + photoFilename;

        try {
            FileOutputStream outstream = new FileOutputStream(picturePath);
            resultPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, outstream);
            outstream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return picturePath;
    }
}
