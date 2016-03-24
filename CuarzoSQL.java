/*
*@Author: Javier Alvarado Reyes
*@Version: 29/03/2015
*/
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CuarzoSQL {//DECLARACION DE LA CLASE
	public String consulta;
        public ResultSet result;
        public Statement stament;
        public int id;

	private Connection conexion;//VARIABLE DE INSTANCIA DE TIPO CONNECTION
	
	public CuarzoSQL()
	{}
	/*A CONTINUACION SE ESTA DECLARANDO EL DRIVER DE CONEXION CON MICROSOFT ACCESS*/
	public boolean CONECTAR_DATABASE() {
	   String nombreBasedeDatosAccess="BasedeDatos.mdb";
             //cuidado aca, la base de datos se encuentra aca, asi:
           String lugardelaBasedeDatos= System.getProperty("user.dir")+"\\base\\"+nombreBasedeDatosAccess;
     
	    try {
	        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
	        conexion = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + lugardelaBasedeDatos);
	               
            } catch (Exception ex) {
	        ex.printStackTrace();
	        return false;
	    }
	    return true;
	}
	//INSERT, UPDATE, DELETE
        public void EJECUTAR_OPERACION_SQL(String sql){
            try{
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(sql);
            }catch(Exception e){
            e.printStackTrace();
            }
        }
        //JTABLE
        public ResultSet LISTAR_PARA_TABLA(String Cad){
            try{
            PreparedStatement da = conexion.prepareStatement(Cad);
            ResultSet tbl = da.executeQuery();
            return tbl;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            return null;
            }
        }
        //SELECT
        public void EJECUTAR_OPERACION_SELECT(String sql){
    try
    {
      CONECTAR_DATABASE();
      this.consulta = sql;
      this.stament = this.conexion.createStatement();
      this.result = this.stament.executeQuery(this.consulta);
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Error consultado registros " + ex);
    }
  }

        
        /*METODO PARA CERRAR LA CONEXION CON LA BASE DE DATOS*/
	public void CERRAR_CONEXION_DATABASE()
	{
		try{
			this.conexion.close();
		}catch(SQLException e){};
	}
}
