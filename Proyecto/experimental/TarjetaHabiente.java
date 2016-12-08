public class TarjetaHabiente{

	//atributos de la calse tarjetaHabiente

	private String nombre;
	private Integer numeroCuenta;
	private double saldo;
	
	/*
	 * Constructores
	 */
	public TarjetaHabiente(String nombre, Integer numeroCuenta, double saldo){
		this.nombre = nombre;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}

	//metodos de la clase

	public String getNombre(){
		return nombre;
	}

	public Integer getNumeroCuenta(){
		return numeroCuenta;
	}

	public double getSaldo(){
		return saldo;
	}
	
	public void setNombre(String name){
		this.nombre = name;
	}

	public void setNumeroCuenta(Integer numCta){
		this.numeroCuenta = numCta;
	}

	public void setSaldo(double saldo){
		this.saldo = saldo;
	}

	public void consultar(){
		
		System.out.println("TarjetaHabiente: " + getNombre());
		System.out.println("NumCuenta: \t " + getNumeroCuenta());
		System.out.println("Saldo:  \t " + getSaldo());
		
	}
}