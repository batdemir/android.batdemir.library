package com.android.batdemir.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.batdemir.library.databinding.ActivityMainBinding;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = MainActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gp = new Intent(context, RecyclerActivity.class);
                startActivity(gp);
                finish();
            }
        });
    }

    private static class TestingModel implements Serializable {

        @SerializedName("id")
        private Integer id;

        @SerializedName("description")
        private String description;

        @SerializedName("subId")
        private Integer subId;

        @SerializedName("subDescription")
        private String subDescription;

        TestingModel(Integer id, String description, Integer subId, String subDescription) {
            this.id = id;
            this.description = description;
            this.subId = subId;
            this.subDescription = subDescription;
        }

        Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        Integer getSubId() {
            return subId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestingModel)) return false;

            TestingModel that = (TestingModel) o;

            if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
                return false;
            if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
                return false;
            if (getSubId() != null ? !getSubId().equals(that.getSubId()) : that.getSubId() != null)
                return false;
            return getSubDescription() != null ? getSubDescription().equals(that.getSubDescription()) : that.getSubDescription() == null;
        }

        @Override
        public int hashCode() {
            int result = getId() != null ? getId().hashCode() : 0;
            result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
            result = 31 * result + (getSubId() != null ? getSubId().hashCode() : 0);
            result = 31 * result + (getSubDescription() != null ? getSubDescription().hashCode() : 0);
            return result;
        }

        public void setSubId(Integer subId) {
            this.subId = subId;
        }

        String getSubDescription() {
            return subDescription;
        }

        public void setSubDescription(String subDescription) {
            this.subDescription = subDescription;
        }
    }
}
