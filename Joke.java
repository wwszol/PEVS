import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Joke
{

    private String id;
    private int size=0;
    private String jokes;
    private ArrayList<String> searchedVtip = new ArrayList<String>();
    private int status;

    public int getSize()
    {
        return size;
    }

    public ArrayList<String> getSearchedVtip()
    {
        return searchedVtip;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setJokes(String jokes)
    {
        this.jokes = jokes;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getJokes()
    {
        return jokes;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void generateRandom ()
   {
       URL url = null;
       try
       {
           url = new URL("https://icanhazdadjoke.com/");
       }
            catch (MalformedURLException e)
       {
           throw new RuntimeException(e);
       }

       HttpURLConnection connection = null;

       try {
           connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("GET");
           connection.setRequestProperty("Accept", "application/json");
           InputStream responseStream = connection.getResponseCode() / 100 == 2
                   ? connection.getInputStream()
                   : connection.getErrorStream();
           Scanner s = new Scanner(responseStream).useDelimiter("\\A");
           String response = s.next();
           JsonElement filer = JsonParser.parseReader(new StringReader(response));
           JsonObject filerObj = filer.getAsJsonObject();
           String id = filerObj.get("id").getAsString();
           setId(id);
           String dadjoke = filerObj.get("joke").getAsString();
           setJokes(dadjoke);
           int status = filerObj.get("status").getAsInt();
           setStatus(status);

       } catch (IOException e)
       {
           throw new RuntimeException(e);
       }
       connection.disconnect();
   }

   public String termString(String one, String two)
   {
       return one.concat(two);
   }
    public void generatesearch (String searching)
    {
        String link = new String ("https://icanhazdadjoke.com/search?term=");
        String term = new String(searching);
        String hyperlink = termString(link,term);


        URL url = null;
        try {
            url = new URL(hyperlink);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            InputStream responseStream = connection.getResponseCode() / 100 == 2
                    ? connection.getInputStream()
                    : connection.getErrorStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.next();
            JsonElement filer = JsonParser.parseReader(new StringReader(response));
            JsonObject filerObj = filer.getAsJsonObject();
            JsonArray arrayOfJokes = filerObj.get("results").getAsJsonArray();
            int counter=0;
            for (JsonElement vtipek : arrayOfJokes)
            {

                JsonObject searched_joke = vtipek.getAsJsonObject();
                String id2 = searched_joke.get("id").getAsString();
                String dowcip2 = searched_joke.get("joke").getAsString();
                searchedVtip.add(dowcip2);
                setJokes(dowcip2);
                counter++;
                setSize(counter);
            }
            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "Joke{" +
                "size=" + size +
                '}';
    }
}
