package com.example.fybproject.listView.search;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fybproject.R;
import com.example.fybproject.client.ServiceGenerator;
import com.example.fybproject.dto.shopDTO.AnalyzeDTO;
import com.example.fybproject.interceptor.JwtToken;
import com.example.fybproject.mediator.MainUserDataMediator;
import com.example.fybproject.service.ShopService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchListItemAdapter extends RecyclerView.Adapter<SearchListItemAdapter.ItemViewHolder> {
    private ArrayList<SearchListItem> listData;

    private ShopService shopService;

    private Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchlist_item, parent, false);

        this.context = parent.getContext();

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        if (listData != null) {
            return listData.size();
        }
        return 0;
    }

    public void setList(ArrayList<SearchListItem> list){
        this.listData = list;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView shop;
        ImageView img;
        LinearLayout searchItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            shop = itemView.findViewById(R.id.searchShopName);
            img = itemView.findViewById(R.id.searchShopImg);
            searchItem = itemView.findViewById(R.id.searchItem);
        }

        void onBind(SearchListItem data) {
            Log.d(TAG, "data.getShop() : " + data.getShop());
            shop.setText(data.getShop());

            // 이미지 불러오기
            if(data.getSimg() != null) {
                String imageUrl = data.getSimg();
                Glide.with(context).load(imageUrl).into(img);
            }

            searchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = data.getSurl();
                    Context context = view.getContext();
                    Log.d(TAG, "쇼핑몰 주소 : " + url);
                    Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + url));
                    context.startActivity(intentUrl);

                    long sid = getSid(data.getShop());
                    char gender = MainUserDataMediator.getGender();
                    int age = MainUserDataMediator.getAge();

                    //Log.d(TAG, "sid = " + sid + ", gender = " + gender + ", age = " + age + ", url = " + url);
                    AnalyzeDTO analyzeDTO = new AnalyzeDTO(sid, gender, age, url);
                    shopService = ServiceGenerator.createService(ShopService.class, JwtToken.getToken());

                    if (shopService != null) {
                        shopService.postAnalyzeData(analyzeDTO)
                                .enqueue(new Callback<AnalyzeDTO>() {
                                    @Override
                                    public void onResponse(Call<AnalyzeDTO> call, Response<AnalyzeDTO> response) {
                                        AnalyzeDTO data = response.body();
                                        if (response.isSuccessful() == true) {
                                            Log.d(ContentValues.TAG, "UserAnalyze : 성공,\nresponseBody : " + data);
                                            Log.d(ContentValues.TAG, "=====================================================================");
                                        } else {
                                            try {
                                                Log.d(ContentValues.TAG, "UserAnalyze : 실패,\nresponseBody() : " + data + ",\nresponse.code(): " + response.code() + ",\nresponse.errorBody(): " + response.errorBody().string());
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AnalyzeDTO> call, Throwable t) {
                                        Log.d(ContentValues.TAG, "onFailure: " + t.toString());
                                    }
                                });
                    }
                }
            });
        }
    }

    public long getSid(String shop) {
        long sid = 0;

        if (shop.equals("무신사")) sid = 1;
        else if (shop.equals("우신사")) sid = 2;
        else if (shop.equals("크림")) sid = 3;
        else if (shop.equals("브랜디")) sid = 4;
        else if (shop.equals("W컨셉")) sid = 5;
        else if (shop.equals("지그재그")) sid = 6;
        else if (shop.equals("포스티")) sid = 7;
        else if (shop.equals("룩핀")) sid = 8;
        else if (shop.equals("에이블리")) sid = 9;
        else if (shop.equals("하이버")) sid = 10;
        else if (shop.equals("MMMC")) sid = 11;
        else if (shop.equals("머스트잇")) sid = 12;
        else if (shop.equals("트렌비")) sid = 13;
        else if (shop.equals("발란")) sid = 14;
        else if (shop.equals("29cm")) sid = 15;
        else if (shop.equals("Mango")) sid = 16;
        else if (shop.equals("크로켓")) sid = 17;
        else if (shop.equals("Farfetch")) sid = 18;
        else if (shop.equals("스타일쉐어")) sid = 19;
        else if (shop.equals("LFmall")) sid = 20;
        else if (shop.equals("Feellike")) sid = 21;
        else if (shop.equals("무드인블루")) sid = 22;
        else if (shop.equals("필링스")) sid = 23;
        else if (shop.equals("마윤")) sid = 24;
        else if (shop.equals("minitmute")) sid = 25;
        else if (shop.equals("venument")) sid = 26;
        else if (shop.equals("The Barnnet")) sid = 27;
        else if (shop.equals("kindabeige")) sid =28;
        else if (shop.equals("yourclothes_")) sid = 29;
        else if (shop.equals("세임")) sid = 30;
        else if (shop.equals("thebucket")) sid = 31;
        else if (shop.equals("ASIDE")) sid = 32;
        else if (shop.equals("ELLFIVE")) sid = 33;
        else if (shop.equals("all_for_myself_")) sid = 34;
        else if (shop.equals("year_closet")) sid = 35;
        else if (shop.equals("데이도어")) sid = 36;
        else if (shop.equals("멜팅픽셀")) sid = 37;
        else if (shop.equals("유스토리")) sid = 38;
        else if (shop.equals("폴리테루")) sid = 39;
        else if (shop.equals("이에이")) sid = 40;
        else if (shop.equals("해칭룸")) sid = 41;
        else if (shop.equals("mfpen")) sid = 42;
        else if (shop.equals("Art if acts")) sid = 43;
        else if (shop.equals("BEHEAVYER")) sid = 44;
        else if (shop.equals("시도")) sid = 45;
        else if (shop.equals("HERITAGEFLOSS")) sid = 46;
        else if (shop.equals("TheOpenProduct")) sid = 47;
        else if (shop.equals("앵글런")) sid = 48;
        else if (shop.equals("999휴머니티")) sid = 49;

        return sid;
    }
}