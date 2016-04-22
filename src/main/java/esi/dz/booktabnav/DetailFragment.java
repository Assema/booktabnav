package esi.dz.booktabnav;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pc on 04/03/2016.
 */
public class DetailFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment,null);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Book book = (Book) bundle.getSerializable("book");
            ImageView coverImage = (ImageView) v.findViewById(R.id.coverImage);
            TextView textSummary = (TextView) v.findViewById(R.id.summary);
            TextView textYear = (TextView) v.findViewById(R.id.yearText);
            TextView textTitle = (TextView) v.findViewById(R.id.textTitle);
            TextView textEditor = (TextView) v.findViewById(R.id.editorText);
            TextView textAuthor = (TextView) v.findViewById(R.id.textAuthor);
            ((TextView) v.findViewById(R.id.textView)).setVisibility(v.VISIBLE);
            coverImage.setImageResource(book.getCover());
            textSummary.setText(book.getSummary());
            textTitle.setText("Titre: "+book.getTitle());
            textYear.setText("Année d'édition: "+book.getYear());
            textEditor.setText("Editeur: "+book.getEditor());
            textAuthor.setText("Auteur(s): "+book.getAuthors().get(0));
        }
        return v;
    }
}
