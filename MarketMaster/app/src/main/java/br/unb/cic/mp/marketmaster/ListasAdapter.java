package br.unb.cic.mp.marketmaster;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ListasAdapter extends BaseAdapter {

    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private ArrayList<DataSnapshot> mListaSnapshot;

    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            mListaSnapshot.add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public ListasAdapter(Activity activity, DatabaseReference ref, String emailPath) {
        mActivity = activity;
        // Firebase não gosta desses caracteres
        emailPath = emailPath.replace('.', '_');
        emailPath = emailPath.replace('#', '-');
        emailPath = emailPath.replace('$', '+');
        emailPath = emailPath.replace('[', '(');
        emailPath = emailPath.replace(']', ')');
        mDatabaseReference = ref
                .child("usuarios")
                .child(emailPath)
                .child("listas")
                .child("conteudo");
        mDatabaseReference.addChildEventListener(mListener);
        mListaSnapshot = new ArrayList<>();
    }

    static class ViewHolder {
        TextView nomeLista;
        TextView descricaoLista;
        LinearLayout.LayoutParams params;
    }

    @Override
    public int getCount() {
        return mListaSnapshot.size();
    }

    @Override
    public NovaLista getItem(int i) {
        DataSnapshot snapshot = mListaSnapshot.get(i);
        return snapshot.getValue(NovaLista.class);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.uma_lista, viewGroup, false);

            final ViewHolder holder = new ViewHolder();
            holder.nomeLista = view.findViewById(R.id.nome_lista);
            holder.descricaoLista = view.findViewById(R.id.descricao_lista);
            holder.params = (LinearLayout.LayoutParams) holder.nomeLista.getLayoutParams();
            view.setTag(holder);
        }

        final NovaLista lista = getItem(i);
        final ViewHolder holder = (ViewHolder) view.getTag();

        setChatRowAppearance(holder);

        String nomeLista = lista.getNome();
        holder.nomeLista.setText(nomeLista);

        String descricaoLista = lista.getDescricao();
        holder.descricaoLista.setText(descricaoLista);

        return view;
    }

    private void setChatRowAppearance(ViewHolder holder) {
        holder.params.gravity = Gravity.CENTER;
        holder.nomeLista.setLayoutParams(holder.params);
        holder.descricaoLista.setLayoutParams(holder.params);
    }

    public void cleanup() {
        mDatabaseReference.removeEventListener(mListener);
    }

}
