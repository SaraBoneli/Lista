package sara.dayse.lista.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sara.dayse.lista.R;
import sara.dayse.lista.activity.MainActivity;
import sara.dayse.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> items;

    public MyAdapter(MainActivity mainActivity, List<MyItem> items) {
        this.mainActivity = mainActivity;// referência a instância para a classe MainActivity.
        this.items = items;// referência a lista de objetos do tipo MyItem.
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);//obtém inflador de layouts
        View v = inflater.inflate(R.layout.item_list,parent,false);//cria elementos de interface referentes a um item
        // e guarda dentro de um objeto do tipo View
        return new MyViewHolder(v);//guarda objeto do tipo View dentro de um objeto do tipo MyViewHolder
        // ,que é retornado pela função
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //recebe dois parâmetros
        MyItem myItem = items.get(position);//obtém item que será usado para preencher a UI.
        View v = holder.itemView;//obtém o objeto do tipo View que está guardado dentro do ViewHolder.
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);//chama o método findViewById.
        imvfoto.setImageURI(myItem.photo);//preenche UI com os dados do item.

        TextView tvTitle = v.findViewById(R.id.tvTitle);//chama o método findViewById.
        tvTitle.setText(myItem.title);//preenche UI com os dados do item.

        TextView tvdesc = v.findViewById(R.id.tvDesc);//chama o método findViewById.
        tvdesc.setText(myItem.description);//preenche UI com os dados do item.

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
