package fr.ec.producthunt.data.database;

import android.database.Cursor;
import fr.ec.producthunt.data.model.Post;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammed Boukadir  @:mohammed.boukadir@gmail.com
 */
public class PostDao {

    private final ProductHuntDbHelper productHuntDbHelper;

    public PostDao(ProductHuntDbHelper productHuntDbHelper) {
        this.productHuntDbHelper = productHuntDbHelper;
    }

    public long save(Post post) {
        //-------------------------------
        productHuntDbHelper.getWritableDatabase().delete(DataBaseContract.PostTable.TABLE_NAME, "id ="+String.valueOf(post.getId()), null);
        //-------------------------------
        return productHuntDbHelper.getWritableDatabase()
                .insert(DataBaseContract.PostTable.TABLE_NAME, null, post.toContentValues());
    }

    public List<Post> retrievePosts() {


        Cursor cursor = productHuntDbHelper.getReadableDatabase()
                .query(DataBaseContract.PostTable.TABLE_NAME,
                        DataBaseContract.PostTable.PROJECTIONS,
                        null, null, null, null, null);

        List<Post> posts = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            do {

                Post post = new Post();

                post.setId(cursor.getInt(0));
                post.setTitle(cursor.getString(1));
                post.setSubTitle(cursor.getString(2));
                post.setUrlImage(cursor.getString(3));
                post.setPostUrl(cursor.getString(4));
                //---------------------------------------------------
                post.setDate(cursor.getString(5));
                post.setNbComments(cursor.getInt(6));
                //---------------------------------------------------
                posts.add(post);


            } while (cursor.moveToNext());
        }

        return posts;
    }
}
