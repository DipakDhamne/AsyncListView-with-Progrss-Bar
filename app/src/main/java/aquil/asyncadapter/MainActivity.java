package aquil.asyncadapter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView lstView;
    private String[] texts = {"dipak","rohit","swapnil","somnath","prakash","pritesh","munna","chandu","marty","alex","joe","melman","goria","mort","moto","tigress","poo","stuart","angry red","max"};
    private RelativeLayout layoutProgrss;
    ProgressBar progressBar;
    private TextView tvProgrss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        tvProgrss = (TextView)findViewById(R.id.tvProgrss);
        lstView = (ListView)findViewById(R.id.lstView);
        layoutProgrss = (RelativeLayout)findViewById(R.id.layoutProgrss);
        lstView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, String, Void>{

        private ArrayAdapter<String> adapter;
        private int count=0;

        @Override
        protected void onPreExecute() {
        adapter = (ArrayAdapter<String>) lstView.getAdapter();
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for(String itm : texts){
                publishProgress(itm);
                try {
                    Thread.sleep(200);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            progressBar.setProgress(count);
            tvProgrss.setText(count+"/"+texts.length);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,"All data is updated",Toast.LENGTH_SHORT).show();
            layoutProgrss.setVisibility(View.GONE);

        }
    }
}
