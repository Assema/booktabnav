package esi.dz.booktabnav;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CutomAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Book> bookList;
    // Pour le fitre
    private ValueFilter  valueFilter;
    private List<Book> mFilterList;

    public CutomAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
        // créer une copie de bookList pour le filtre
        this.mFilterList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.list_items, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.coverIcon);
        TextView textTitle = (TextView) convertView.findViewById(R.id.title);
        TextView textAuthors = (TextView) convertView.findViewById(R.id.authors);
        TextView textEditor = (TextView) convertView.findViewById(R.id.editor);
        coverIcon.setImageResource(bookList.get(position).getIconCover());
        textTitle.setText(bookList.get(position).getTitle());
        // Récupérer la liste des auteurs
        List<String> bookAuthors = bookList.get(position).getAuthors();
        // Séparer la liste des auteurs par une virgule
        String authors = bookAuthors.get(0);
        int listSize = bookAuthors.size();
        if (listSize > 1) {
            for (int i = 1; i < listSize; i++) {
                authors = authors + ", " + bookAuthors.get(i);
            }
            textAuthors.setText("Auteur(s): "+ authors);
        }
        textEditor.setText("Editeur: " + bookList.get(position).getEditor());
        return convertView;
    }

    /* Implementation du filtre
       On doit redéfinir la méthode getFilter()
    */
    @Override
    public Filter getFilter() {
       // La méthode retourne un objet de type Filter
        if(valueFilter==null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    // Une nouvelle classe de type Filter est définie
    // Quand on va instancier ValueFilter, deux méthodes sont appelées :  : performFiltering et publishResults

    private class ValueFilter extends Filter {

        // cette méthode effecute le filtre sur la liste des livres
        // une copie de cette liste mFilterList est utilisée

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Book book;
            List<Book> listFilter = new ArrayList<Book>();
            FilterResults filterResults = new FilterResults();
            // véirifer si le texte n'est pas vide
            if (constraint != null && constraint.length() > 0) {
                for (int i = 0; i < mFilterList.size(); i++) {
                    book = mFilterList.get(i);
           // Utiliser la catégorie comme filtre
                    if ((book.getCategory().toUpperCase().contains(constraint.toString().toUpperCase()))) {
                        listFilter.add(book);
                    }
                }
                filterResults.count = listFilter.size();
                filterResults.values = listFilter;
            } else {
                filterResults.count = mFilterList.size();
                filterResults.values = mFilterList;
            }
            return filterResults;
        }

        // Cette méthode permet d'afficher la nouvelle listView en appelant notifyDataSetChanged()
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            bookList = (List<Book>) results.values;
            notifyDataSetChanged();

        }
    }
}
