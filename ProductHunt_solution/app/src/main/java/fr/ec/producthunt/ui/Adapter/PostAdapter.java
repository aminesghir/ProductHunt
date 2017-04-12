package fr.ec.producthunt.ui.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import fr.ec.producthunt.R;
import fr.ec.producthunt.data.model.Post;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohammed Boukadir  @:mohammed.boukadir@gmail.com
 */
public class PostAdapter extends BaseAdapter {

  private static final int TYPE_HEADER = 1;
  private static final int TYPE_ITEM_LIST = 2;
  private List<Post> datasource = Collections.EMPTY_LIST;
  int type;

  public PostAdapter() {
  }

  @Override public int getCount() {
    return datasource.size();
  }

  @Override public Object getItem(int position) {
    return datasource.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {

    if(convertView == null) {
        if(type == TYPE_HEADER){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent, false);
        }else{
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent, false);
        }

    }

    Post post = datasource.get(position);
    TextView title = (TextView) convertView.findViewById(R.id.title);
    title.setText(post.getTitle());
    TextView subTitle = (TextView) convertView.findViewById(R.id.sub_title);
    subTitle.setText(post.getSubTitle());
    TextView date = (TextView) convertView.findViewById(R.id.date);
    date.setText(post.getDate());

    ImageView imageView = (ImageView) convertView.findViewById(R.id.img_product);
    Picasso.with(parent.getContext()).load(post.getUrlImage()).into(imageView);
    return convertView;
  }

  @Override public int getItemViewType(int position){
    if(position == 0){
      type = TYPE_HEADER;
    }
    else{
      type = TYPE_ITEM_LIST;
    }

    return type;
  }

  @Override public int getViewTypeCount(){
      return 2;
  }

  private class PostAndView {
    public Post post;
    public View view;
    public Bitmap bitmap;
  }


  public void showListPost(List<Post> posts) {
    notifyDataSetInvalidated();
    this.datasource = posts;
    notifyDataSetChanged();
  }
}
