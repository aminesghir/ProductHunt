package fr.ec.producthunt.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import fr.ec.producthunt.R;
import fr.ec.producthunt.data.DataProvider;

/**
 * Created by user on 14/04/2017.
 */

public class CommentActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getIntent().getStringExtra("POST_TITLE"));


        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshComments();
            }
        });

        ListView listComments = (ListView) findViewById(R.id.comments_list);
        adapter = new ArrayAdapter<String>(this, R.layout.item_comment);
        listComments.setAdapter(adapter);


        loadComments();


    }

    private void refreshComments() {
        loadComments();
        ((SwipeRefreshLayout)findViewById(R.id.swiperefresh)).setRefreshing(false);
    }

    private void loadComments() {
        FetchPostsAsyncTask fetchPstsAsyncTask = new FetchPostsAsyncTask();
        fetchPstsAsyncTask.execute();
    }

    private class FetchPostsAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            adapter.clear();
            adapter.notifyDataSetChanged();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {

            ArrayList<String> s = new ArrayList<>();
            try {
                Intent intent = getIntent();
                JSONObject postDetails;
                if(intent.getExtras().containsKey("POST_ID")) {
                    postDetails = new JSONObject(DataProvider.getPostDetails(intent.getIntExtra("POST_ID", 0)));
                    Log.i("LOOOOOL", DataProvider.getPostDetails(intent.getIntExtra("POST_ID", 0)));
                }else {
                    throw new IllegalStateException("Il faut passer l'ID du post");
                }

                JSONArray comments = postDetails.getJSONObject("post").getJSONArray("comments");


                for (int i = 0; i < comments.length(); i++) {
                    JSONObject comment = (JSONObject)comments.get(i);
                    String text = comment.getString("body");

                    s.add(text);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {

            int i;

            for(i=0; i<s.size(); i++){
                adapter.add(s.get(i));
                Log.i("comment", s.get(i));
            }

            adapter.notifyDataSetChanged();


        }
    }
}
