package ru.geekbrains.gb03_android_on_java_calculator;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public abstract class Expression implements Parcelable {

    abstract public String convertToString();

    abstract public BigDecimal calc() throws InvalidExpression;

    @NonNull
    @Override
    public String toString() {
        return convertToString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(toString());
    }

    public static final Parcelable.Creator<Expression> CREATOR = new Parcelable.Creator<Expression>() {
        // распаковываем объект из Parcel
        public Expression createFromParcel(Parcel in) {
            try {
                String str = in.readString();
                return ExpressionBuilder.buildFromString(str);
            } catch (InvalidExpression invalidExpression) {
                return ExpressionBuilder.zeroExpression();
            }
        }

        public Expression[] newArray(int size) {
            return new Expression[size];
        }
    };
}
