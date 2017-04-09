package fr.ec.producthunt;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ViewAnimator;
import fr.ec.producthunt.ui.Adapter.PostAdapter;
import fr.ec.producthunt.data.DataProvider;
import fr.ec.producthunt.data.JsonPostParser;
import fr.ec.producthunt.data.model.Post;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  public static final int NB_ITEM = 400;

  private PostAdapter adapter;
  private ListView listView;
  private ProgressBar progressBar;
  private ViewAnimator viewAnimator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Utilisé une toolbar au lieu d'un actionbar
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setTitle(R.string.app_name);

    listView = (ListView) findViewById(R.id.listview);
    adapter = new PostAdapter();
    listView.setAdapter(adapter);
    progressBar = (ProgressBar) findViewById(R.id.progress);
    viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);

    viewAnimator.setDisplayedChild(1);


  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {

    //Attacher le main menu au menu de l'activity
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.main, menu);

    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.refresh:
        refreshPosts();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void refreshPosts() {
    PostsAsyncTask postsAsyncTask = new PostsAsyncTask();
    postsAsyncTask.execute();
  }

  private class PostsAsyncTask extends AsyncTask<Void, Integer, List<Post>> {

    //Do on Main Thread
    @Override protected void onPreExecute() {
      super.onPreExecute();
      Log.d(TAG, "onPreExecute: ");
      /*
      progressBar.setVisibility(View.VISIBLE);
      listView.setVisibility(View.GONE);
      */
      viewAnimator.setDisplayedChild(0);
    }

    //Do on Background Thread
    @Override protected List<Post> doInBackground(Void... params) {

      String posts = DataProvider.getPostsFromWeb();
      Log.d(TAG, "doInBackground: Posts :" + posts);

      return JsonPostParser.jsonToPosts(posts);
    }

    //Do on Main Thread
    @Override protected void onPostExecute(List<Post> result) {
      super.onPostExecute(result);
      Log.d(TAG, "onPostExecute() called with: " + "result = [" + result + "]");
      if (result != null) {

        adapter.showListPost(result);
       // progressBar.setVisibility(View.GONE);
       // listView.setVisibility(View.VISIBLE);
        viewAnimator.setDisplayedChild(1);

      }
    }
  }
}
