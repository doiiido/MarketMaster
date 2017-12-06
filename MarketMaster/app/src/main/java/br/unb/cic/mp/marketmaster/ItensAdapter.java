package br.unb.cic.mp.marketmaster;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ItensAdapter extends BaseAdapter {

    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private ArrayList<DataSnapshot> mItemSnapshot;

    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            mItemSnapshot.add(dataSnapshot);
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

    public ItensAdapter(Activity activity, DatabaseReference ref, String emailPath) {
        mActivity = activity;
        // Firebase não gosta desses caracteres
        emailPath = emailPath.replace('.', '_');
        emailPath = emailPath.replace('#', '-');
        emailPath = emailPath.replace('$', '+');
        emailPath = emailPath.replace('[', '(');
        emailPath = emailPath.replace(']', ')');
        mDatabaseReference = ref
                .child("itens")
                .child("conteudo");
        mDatabaseReference.addChildEventListener(mListener);
        mItemSnapshot = new ArrayList<>();
    }

    static class ViewHolder {
        TextView nome;
        LinearLayout.LayoutParams params;
    }

    @Override
    public int getCount() {
        return mItemSnapshot.size();
    }

    @Override
    public NovoItem getItem(int i) {
        DataSnapshot snapshot = mItemSnapshot.get(i);
        return snapshot.getValue(NovoItem.class);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.um_item, viewGroup, false);

            final ViewHolder holder = new ViewHolder();
            holder.nome = view.findViewById(R.id.nome_item);
            holder.params = (LinearLayout.LayoutParams) holder.nome.getLayoutParams();
            view.setTag(holder);
        }

        final NovoItem item = getItem(i);
        final ViewHolder holder = (ViewHolder) view.getTag();

        setChatRowAppearance(holder);

        String nomeItem = item.getNome();
        holder.nome.setText(nomeItem);

        return view;
    }

    private void setChatRowAppearance(ViewHolder holder) {
        holder.params.gravity = Gravity.CENTER;
        holder.nome.setLayoutParams(holder.params);
    }

    public void cleanup() {
        mDatabaseReference.removeEventListener(mListener);
    }

}
