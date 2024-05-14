package sara.dayse.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    Uri selectPhotoLocation = null;//guarda o endereço URI da foto escolhida pelo usuário

    public Uri getSelectPhotoLocation() {//obtém lista de itens
        return selectPhotoLocation;//obtém lista de itens
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation) {//seta o
        //endereço URI dentro do ViewModel
        this.selectPhotoLocation = selectPhotoLocation;//seta o
        //endereço URI dentro do ViewModel
    }
}
