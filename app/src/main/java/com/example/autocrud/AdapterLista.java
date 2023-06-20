package com.example.autocrud;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.autocrud.Controler.AutoBD;
import com.example.autocrud.Modelo.Auto;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdapterLista extends RecyclerView.Adapter<AdapterLista.AutoViewHolder>{

    ArrayList <Auto> listaAutos;
    private boolean isClickable = true;

    public void setCardViewClickable(boolean clickable) {
        isClickable = clickable;
    }

    public AdapterLista(ArrayList<Auto> listaAutos){
        this.listaAutos = listaAutos;
    }

    @NonNull
    @Override
    public AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crear la vista del elemento de lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,false);
        return new AutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLista.AutoViewHolder holder, int position) {

        final Auto autop = listaAutos.get(position);

        holder.P1.setText(autop.getPatente().substring(0,2).toUpperCase());
        holder.P2.setText(autop.getPatente().substring(2,4).toUpperCase());
        holder.P3.setText(autop.getPatente().substring(4,6).toUpperCase());
        holder.Item.setText(autop.getMarca().toUpperCase());
        holder.Subitem.setText(autop.getModelo().toUpperCase());
        holder.Info.setText("COLOR: "+autop.getColor()+
                "\nAÑO: "+autop.getAnio()+
                "\nCILINDRAJE: "+autop.getCilindrada()+"cc");

        // Verificar si el onClick está habilitado o deshabilitado
        if (isClickable){
            // Habilitar el onClick
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Eliminar Registro");
                    builder.setMessage("Deseas eliminar este registro?");

                    builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String dato = new String();
                            AutoBD autoBD = new AutoBD(v.getContext());
                            SQLiteDatabase SQLiteDB = autoBD.getReadableDatabase();
                            SQLiteDatabase SQLiteDBg = autoBD.getWritableDatabase();
                            try {
                                Cursor c = SQLiteDB.rawQuery("SELECT PATENTE FROM vehiculos WHERE patente = '" + autop.getPatente()+"'", null);
                                if (c.moveToFirst()) {
                                    dato = c.getString(0);

                                    //Toast.makeText(v.getContext(), "PATENTE:" + dato, Toast.LENGTH_SHORT).show();
                                    SQLiteDBg.delete("vehiculos", "PATENTE = '" + dato + "'", null);
                                    SQLiteDBg.close();
                                    Toast.makeText(v.getContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
                                    listaAutos.remove(autop);
                                    notifyDataSetChanged();
                                }else{
                                    Toast.makeText(v.getContext(), "No hay datos para eliminar", Toast.LENGTH_SHORT).show();
                                }
                                c.close();
                            }catch (Exception e){
                                Log.d("Error: ",e.getMessage());
                                Toast.makeText(v.getContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }SQLiteDB.close();
                        }//Fin metodo onClick Positive Button
                    });
                    builder.setNegativeButton("Cancelar",null);
                    builder.show();
                }//Fin metodo onClick CardView
            });
        }else holder.cardView.setOnClickListener(null);// Deshabilitar el onClick
    }

    @Override
    public int getItemCount() {
        return listaAutos.size();
    }

    public class AutoViewHolder extends RecyclerView.ViewHolder {
        TextView P1,P2,P3,Item,Subitem,Info;
        CardView cardView;
        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);

            P1 = itemView.findViewById(R.id.txtPat1);
            P2 = itemView.findViewById(R.id.txtPat2);
            P3 = itemView.findViewById(R.id.txtPat3);
            Item = itemView.findViewById(R.id.txtItem);
            Subitem = itemView.findViewById(R.id.txtSubItem);
            Info = itemView.findViewById(R.id.txtInfo);
            cardView = itemView.findViewById(R.id.cardDatos);
        }
    }

}
