package TrabalhoFinal;

public class InformBeer {
	private int ABV;
	private int IBU;
	private int SMR;
	public InformBeer(int aBV, int iBU, int sMR) {
		ABV = aBV;
		IBU = iBU;
		SMR = sMR;
	}
	public String toString() {
		return this.ABV + "," + this.IBU + "," + this.SMR + ",";
	}
	
}
