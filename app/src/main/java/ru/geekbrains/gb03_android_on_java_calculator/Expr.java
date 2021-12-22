package ru.geekbrains.gb03_android_on_java_calculator;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public abstract class Expr implements Serializable, Parcelable {

    private static final Pattern doubleNumPattern = Pattern.compile("^(-?)(0|([1-9][0-9]*))*(\\.[0-9]*)?$");

    private static Expr build(String str) throws InvalidExpression {
        try {
            String noZeroStr = str.replaceFirst("^0+", "");
            if (noZeroStr.isEmpty())
                return NumExpr.ZERO;
            else if (noZeroStr.equals("."))
                str = "0.";
            else if (noZeroStr.equals("-."))
                str = "-0.";

            if (doubleNumPattern.matcher(str).find())
                return new NumExpr(str);

            throw new InvalidExpression(str);
        } catch (Exception e) {
            throw new InvalidExpression(e.getMessage());
        }
    }

    public Expr concat(String str) {
        try {
            if (this == NumExpr.ZERO)
                return build(str);

            return build(this.toString() + str);
        } catch (InvalidExpression invalidExpression) {
            return this;
        }
    }

    public Expr delete() {
        try {
            String str = this.toString();
            if (this == NumExpr.ZERO || str.length() <= 1)
                return NumExpr.ZERO;

            return build(str.substring(0, str.length() - 1));
        } catch (InvalidExpression invalidExpression) {
            return this;
        }
    }

    public Expr simplify() {
        try {
            BigDecimal res = calc();
            if (res.equals(BigDecimal.ZERO))
                return NumExpr.ZERO;
            return new NumExpr(res.toPlainString());
        } catch (InvalidExpression invalidExpression) {
            return this;
        }
    }

    abstract public BigDecimal calc() throws InvalidExpression;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(toString());
    }

    public static final Parcelable.Creator<Expr> CREATOR = new Parcelable.Creator<Expr>() {
        // распаковываем объект из Parcel
        public Expr createFromParcel(Parcel in) {
            try {
                String str = in.readString();
                return Expr.build(str);
            } catch (InvalidExpression invalidExpression) {
                return NumExpr.ZERO;
            }
        }

        public Expr[] newArray(int size) {
            return new Expr[size];
        }
    };
}
