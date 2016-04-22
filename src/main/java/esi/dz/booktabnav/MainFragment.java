package esi.dz.booktabnav;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 17/03/2016.
 */
public class MainFragment extends Fragment {

    CutomAdapter cutomAdapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, null);
        ListView listView = (ListView) v.findViewById(R.id.listView);
        cutomAdapter = new CutomAdapter(getActivity(),getBookList());
        Bundle bundle = getArguments();
        int position = bundle.getInt("pos");
        if (position==0) {
            cutomAdapter.getFilter().filter("Base de données&Système");
        }
        else {
            if (position==1) {
                cutomAdapter.getFilter().filter("Génie logiciel");
                }
            else {
                cutomAdapter.getFilter().filter("Programmation");

            }
        }

        listView.setAdapter(cutomAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showView((Book) cutomAdapter.getItem(position));
            }
        });
        return v;
    }

    public List<Book> getBookList() {
        String[] listSummary = getResources().getStringArray(R.array.summary);
        List<Book> bookList = new ArrayList<Book>();
        // le 1er livre
        Book book = new Book();
        book.setTitle("Design Patterns in Java");
        List authors = new ArrayList();
        authors.add("Steven John Metsker");
        authors.add("William C. Wake");
        book.setAuthors(authors);
        book.setEditor("Addison-Wesley Professional");
        book.setIconCover(R.drawable.ic_dpjava);
        book.setCover(R.drawable.ic_dpjavacover);
        book.setYear("2006");
        book.setSummary(listSummary[0]);
        book.setCategory("Génie logiciel");
        bookList.add(book);
        // le 2eme livre
        book = new Book();
        //book.setTitle("UML 2.0 in a Nutshell");
        book.setTitle("UML 2.0 in a Nutshell UML 2.0");
        authors = new ArrayList();
        authors.add("Dan Pilone");
        authors.add("Neil Pitman");
        book.setAuthors(authors);
        book.setEditor("O\'Reilly");
        book.setIconCover(R.drawable.ic_uml_2);
        book.setCover(R.drawable.ic_uml_2cover);
        book.setYear("2005");
        book.setCategory("Génie logiciel");
        book.setSummary(listSummary[1]);

        bookList.add(book);
        // le 3eme livre
        book = new Book();
        book.setTitle("Microsoft SQL Server 2012 Pocket Consultant");
        authors = new ArrayList();
        authors.add("William R. Stanek");
        book.setAuthors(authors);
        book.setEditor("Microsoft Press");
        book.setIconCover(R.drawable.ic_sqlpc);
        book.setCover(R.drawable.ic_sqlpc_cover);
        book.setYear("2012");
        book.setSummary(listSummary[2]);
        book.setCategory("Base de données&Système");
        bookList.add(book);
        // le 4ème livre
        book = new Book();
        book.setTitle("Android UI Fundamentals: Develop & Design");
        authors = new ArrayList();
        authors.add("Jason Ostrander");
        book.setAuthors(authors);
        book.setEditor("Peachpit Press");
        book.setIconCover(R.drawable.ic_androidfd);
        book.setCover(R.drawable.ic_androidfdcover);
        book.setYear("2012");
        book.setSummary(listSummary[3]);
        book.setCategory("Programmation");
        bookList.add(book);
        // le 5ème livre
        book = new Book();
        book.setTitle("Programming in Objective-C");
        authors = new ArrayList();
        authors.add("Stephen Kochan");
        book.setAuthors(authors);
        book.setEditor("Developer's Library");
        book.setIconCover(R.drawable.ic_objectivec);
        book.setCover(R.drawable.ic_objectivecover);
        book.setYear("2012");
        book.setSummary(listSummary[4]);
        book.setCategory("Programmation");
        bookList.add(book);
        // le 6 ème livre
        book = new Book();
        book.setTitle("Learning Agile");
        authors = new ArrayList();
        authors.add("Andrew Stellman");
        authors.add("Jennifer Greene");
        book.setAuthors(authors);
        book.setEditor("Kindle Edition");
        book.setIconCover(R.drawable.ic_agile);
        book.setCover(R.drawable.ic_agilecovrer);
        book.setYear("2014");
        book.setSummary(listSummary[5]);
        book.setCategory("Génie logiciel");
        bookList.add(book);
        // le 7 ème livre
        book = new Book();
        book.setTitle("Learning the UNIX Operating System");
        authors = new ArrayList();
        authors.add("Jerry Peek");
        authors.add("Grace T-Gonguet");
        authors.add("John Strang");
        book.setAuthors(authors);
        book.setEditor("O'Reilly Media, Inc.");
        book.setIconCover(R.drawable.ic_unixicon);
        book.setCover(R.drawable.ic_unixicover);
        book.setYear("2002");
        book.setSummary(listSummary[6]);
        book.setCategory("Base de données&Système");
        bookList.add(book);
        return bookList;
    }

    public boolean isTwoPane() {

        View v  = getActivity().findViewById(R.id.frameLayout);
        return (v!=null);

    }

    public void showView (Book book) {
        if (isTwoPane()) {
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("book", book);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, detailFragment);

            ft.commit();

        } else {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("book", book);
            startActivity(intent);
        }

    }

    }
