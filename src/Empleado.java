public class Empleado {
    private String nombres;
    private double sueldoMensual;
    private String cedula;
    private String telefono;
    private double AporteSeguroSocial;
    private double ImpuestoRenta;

    public Empleado(String nombres, double sueldo, String cedula, String telefono) {
        this.nombres = nombres;
        this.sueldoMensual = sueldo;
        this.cedula = cedula;
        this.telefono = telefono;
        this.AporteSeguroSocial = sueldo * 0.0935;
        calcularImpuestoRentaAnual();
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setSueldoMensual(double sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombres() {
        return nombres;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public String getCedula() {
        return cedula;
    }

    public double getImpuestoRenta() {
        return ImpuestoRenta;
    }

    public double getAporteSeguroSocial() {
        return AporteSeguroSocial;
    }

    public void calcularImpuestoRentaAnual() {
//Primero tengo que invocar este para luego hacer todos los calculos
        double ValorDelAporteAnual;
        double SueldoAnual = getSueldoMensual() * 12;
        double Exceso;

        if (SueldoAnual <= 5000) {
            ValorDelAporteAnual = 0;
        } else if (SueldoAnual > 5000 && SueldoAnual <= 10000) {
            Exceso = SueldoAnual - 5000;
            ValorDelAporteAnual = Exceso * 0.10;
        } else if (SueldoAnual > 10000 && SueldoAnual <= 18000) {
            Exceso = SueldoAnual - 10000;
            ValorDelAporteAnual = Exceso * 0.20;
        } else {
            Exceso = SueldoAnual - 18000;
            ValorDelAporteAnual = Exceso * 0.30;
        }

        this.ImpuestoRenta = ValorDelAporteAnual;
    }

    public double calcularImpuestoRentaMensual() {
        return getImpuestoRenta() / 12;
    }

    public double calcularAporteSeguroAnual() {
        return getAporteSeguroSocial() * 12;
    }

    public double sueldoMensualParaRecibir() {
        return getSueldoMensual() - calcularImpuestoRentaMensual() - getAporteSeguroSocial();
    }

    public double sueldoAnualParaRecibir() {
        double sueldoAnual = getSueldoMensual() * 12;
        return  sueldoAnual - getImpuestoRenta() - calcularAporteSeguroAnual();
    }

    @Override
    public String toString() {
        return  "--------------------------------" + '\n' +
                "Nombres: " + nombres + '\n' +
                "Sueldo mensual: " + sueldoMensual + '\n' +
                "Cédula: " + cedula + '\n' +
                "Teléfono: " + telefono + '\n' +
                "Aporte al seguro social mensual: " + AporteSeguroSocial + '\n' +
                "Aporte al seguro social anual: " + calcularAporteSeguroAnual() + '\n' +
                "Impuesto a la renta mensual: " + calcularImpuestoRentaMensual() + '\n' +
                "Impuesto a la renta anual: " + ImpuestoRenta + '\n' +
                "Sueldo mensual a recibir: " + sueldoMensualParaRecibir() + '\n' +
                "Sueldo anual a recibir: " + sueldoAnualParaRecibir() + '\n';
    }
}
