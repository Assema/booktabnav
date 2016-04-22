package esi.dz.booktabnav;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if ((config.orientation ==
                Configuration.ORIENTATION_LANDSCAPE)
                &&(config.smallestScreenWidthDp>=600))
        {
            finish();
        }
        else {
            setContentView(R.layout.activity_detail);
            Book book = (Book) getIntent().getSerializableExtra("book");
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("book", book);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout,detailFragment);
            ft.commit();
        }
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
