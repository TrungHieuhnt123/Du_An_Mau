
package Entity;

public class HocVien {
    String maNH;
    int maKH,maHV;
    double diemTB;

    public HocVien() {
    }

    public HocVien(int maHV, int maKH, String maNH, double diemTB) {
        this.maHV = maHV;
        this.maKH = maKH;
        this.maNH = maNH;
        this.diemTB = diemTB;
    }

    public int getMaHV() {
        return maHV;
    }

    public void setMaHV(int maHV) {
        this.maHV = maHV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }
    
    
}
