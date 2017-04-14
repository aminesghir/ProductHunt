package fr.ec.producthunt.data;

import android.util.Log;

import fr.ec.producthunt.data.model.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Mohammed Boukadir  @:mohammed.boukadir@gmail.com
 */
public class JsonPostParser {

    public static List<Post> jsonToPosts(String json) {

        try {

            JSONObject postsRespnse = new JSONObject(json);
            JSONArray postsJson = postsRespnse.getJSONArray("posts");

            int size = postsJson.length();

            ArrayList<Post> posts = new ArrayList(size);

            for (int i = 0; i < postsJson.length(); i++) {
                JSONObject postJson = (JSONObject) postsJson.get(i);

                posts.add(jsonToPost(postJson));
            }

            return posts;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Post jsonToPost(JSONObject postJson) throws JSONException, ParseException {
        Post post = new Post();
        post.setId(postJson.getInt("id"));
        post.setTitle(postJson.getString("name"));
        post.setSubTitle(postJson.getString("tagline"));
        post.setPostUrl(postJson.getString("redirect_url"));

        //Image url
    /*"thumbnail": {
      "id": 138979,
          "media_type": "image",
          "image_url": "https://ph-files.imgix.net/3c7be2fc-6084-4de0-8c39-40fde0b38d0a?auto=format&w=430&h=570&fit=max",
          "metadata": {}
    }*/

        JSONObject thumbnail = postJson.getJSONObject("thumbnail");
        String imageUrl =thumbnail.getString("image_url");

        post.setUrlImage(imageUrl);


        //-----------------------------


        SimpleDateFormat exDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        post.setDate(date.format(exDate.parse(postJson.getString("created_at"))));


        //JSONObject a = new JSONObject(DataProvider.getPostDetails(postJson.getInt("id")));
        //JSONObject b = a.getJSONObject("post");
        post.setNbComments(postJson.getInt("comments_count"));


        //---------------------------------


        return post;
    }
}
