package fr.ec.producthunt.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

import fr.ec.producthunt.R;

/**
 * Created by user on 14/04/2017.
 */

public class CommentActivity extends AppCompatActivity {

    public static final int POST_ID = 0;
    ListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listComments = (ListView) findViewById(R.id.comments_list);
        listComments.setAdapter(adapter);
    }
}
