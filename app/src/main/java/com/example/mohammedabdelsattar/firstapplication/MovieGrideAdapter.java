package com.example.mohammedabdelsattar.firstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.Movie;


/**
 * Created by Mohammed Abdelsattar on 3/17/2016.
 */


public class MovieGrideAdapter extends BaseAdapter {

ArrayList<Movie> moviesData;
        Context context;
        private static LayoutInflater inflater=null;

        public MovieGrideAdapter(MainActivity mainActivity,ArrayList<Movie> moviesData) {
            // TODO Auto-generated constructor stub
            this.moviesData=moviesData;
            context=mainActivity;
            inflater = (LayoutInflater)context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return moviesData.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

       public class MYtag
       {
       TextView tv;
        ImageView img;
       }

        @Override
        public View getView(final int position, View convertView/*this view passes by List view */, ViewGroup parent) {
            // TODO Auto-generated method stub
            //have to do
            //do debug here
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.grid_item, null);
                MYtag mYtag=new MYtag();
                mYtag.tv=(TextView) convertView.findViewById(R.id.textView1);
                mYtag.img=(ImageView) convertView.findViewById(R.id.imageView1);

                mYtag.tv.setText(moviesData.get(position).title);
                //holder.tv = (TextView) convertView.findViewById(R.id.textView1);
                //holder.img = (ImageView) convertView.findViewById(R.id.imageView1);
                //holder.tv.setTag("text");
                Picasso.with(context)
                        .load("http://image.tmdb.org/t/p/w342"+moviesData.get(position).poster_path)
                        .into(mYtag.img);
                convertView.setTag(mYtag);
            }

else {
                //tag some times called view holder
//builder services or loaders
                //Synk Adapter in lesson 5 or 6
                //when rotate screen
                MYtag mymy= (MYtag) convertView.getTag();
          //      mymy.img.setBackground();
                mymy.tv.setText(moviesData.get(position).title);
                //holder.tv = (TextView) convertView.findViewById(R.id.textView1);
                //holder.img = (ImageView) convertView.findViewById(R.id.imageView1);
                //holder.tv.setTag("text");
               // mymy.img.setImageResource(R.drawable.common_full_open_on_phone);
 Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185"+moviesData.get(position).poster_path)
                .into(mymy.img);


                convertView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        //Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
                    }
                });
            }
            return convertView;
        }


}
