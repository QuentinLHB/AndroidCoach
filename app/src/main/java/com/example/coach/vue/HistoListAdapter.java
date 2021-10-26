package com.example.coach.vue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {
    /**
     * Sous-Classe permettant de lier les objets graphiques d'une ligne (View) d'une ListView.
     * A lier au Tag d'une View.
     */
    private class ViewProperties {
        TextView txtListDate;
        TextView txtListIMG;
        ImageButton btnListSuppr;
    }

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Profil> lstProfils;

    public HistoListAdapter(Context context, ArrayList<Profil> lstProfils) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.lstProfils = lstProfils;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return lstProfils.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return lstProfils.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewProperties viewProperties;
        Profil profil = lstProfils.get(position);

        if (convertView == null) {
            viewProperties = new ViewProperties();
            convertView = inflater.inflate(R.layout.layout_liste_histo, null);
            viewProperties.txtListDate = (TextView) convertView.findViewById(R.id.txtListDate);
            viewProperties.txtListIMG = (TextView) convertView.findViewById(R.id.txtListIMG);
            viewProperties.btnListSuppr = (ImageButton) convertView.findViewById(R.id.imgButSuppr);
            viewProperties.btnListSuppr.setTag(position);
            convertView.setTag(viewProperties);


        } else {
            viewProperties = (ViewProperties) convertView.getTag();
        }

        String img = MesOutils.format2Decimal(profil.getImg());
        viewProperties.txtListIMG.setText(img);
        String date = MesOutils.convertDateToString(profil.getDateMesure());
        viewProperties.txtListDate.setText(date);

        viewProperties.btnListSuppr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // v = l'objet concerné par le clique, on récupère sa position.
                int indice = (int) v.getTag();
                Controle.getInstance(null).supprProfil(lstProfils.get(indice));
                notifyDataSetChanged();
            }
        });

        viewProperties.txtListDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int) viewProperties.btnListSuppr.getTag();
                HistoActivity activity = (HistoActivity) context;
                activity.afficheProfil(lstProfils.get(indice));
            }
        });

        viewProperties.txtListIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int) viewProperties.btnListSuppr.getTag();
                HistoActivity activity = (HistoActivity) context;
                activity.afficheProfil(lstProfils.get(indice));
            }
        });

        return convertView;

    }

}
