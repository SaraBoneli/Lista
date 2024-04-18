package sara.dayse.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sara.dayse.lista.R;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);//obtém botão
        fabAddItem.setOnClickListener(new View.OnClickListener() { //registra um ouvidor de cliques
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NewItemActivity.class);//Intent explícito para navegar para NewItemAcitvity
                startActivityForResult(i, NEW_ITEM_REQUEST);//assume que a Activity destino (NewItemAcitivity)
                //irá retornar com dados em algum momento para
                //a Activity que iniciou a navegação (MainActivity)

            }
        });
    }
}