package pt.ipp.estg.cmu_tp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pt.ipp.estg.cmu_tp.DetailedInfo;
import pt.ipp.estg.cmu_tp.Detailed_Info;
import pt.ipp.estg.cmu_tp.POI_info;
import pt.ipp.estg.cmu_tp.R;
import pt.ipp.estg.cmu_tp.POI;

import java.util.List;

public class Adapter_Posto extends RecyclerView.Adapter<Adapter_Posto.ViewHolder> {

    private Context mContext;
    private List<POI> mListPoi;

    public Adapter_Posto(Context mContext, List<POI> mPoi) {
        this.mContext = mContext;
        this.mListPoi = mPoi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View concertView = inflater.inflate(R.layout.info_poi, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(concertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        POI poi= mListPoi.get(i);
        TextView text = viewHolder.nPoi;
        text.setText(poi.adressInfo.getAdressLine1());


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , Detailed_Info.class);
                POI poi= mListPoi.get(i);
                int id =poi.getID();
                String nome = poi.adressInfo.getTitle();
                intent.putExtra("nome",nome);
                String morada = poi.adressInfo.getAdressLine1();
                intent.putExtra("morada",morada);
                String cidade = poi.adressInfo.getTown();
                intent.putExtra("cidade",cidade);


                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListPoi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nPoi , nLocal_a;
        public Button btn1_a;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nPoi = (TextView) itemView.findViewById(R.id.nome_poi);


        }}
}
