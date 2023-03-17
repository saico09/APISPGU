package cl.ips.pacs.vo;
public class Product {

	private String type;
	private int discount;
	private int iRUN;
	private String vApellidoPaterno;
	
	public String getvApellidoPaterno() {
		return vApellidoPaterno;
	}

	public void setvApellidoPaterno(String vApellidoPaterno) {
		this.vApellidoPaterno = vApellidoPaterno;
	}

	public int getiRUN() {
		return iRUN;
	}

	public void setiRUN(int iRUN) {
		this.iRUN = iRUN;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Product [type=" + type + ", discount=" + discount + ", iRUN=" + iRUN + ", vApellidoPaterno="
				+ vApellidoPaterno + "]";
	}

	
}