package trankhanhduy.pro;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ClassEdit extends JDialog {
	JTextField txtMaGhe,txtMaKH,txtTenKH,txtCMND,txtSDT,txtTenTau,txtSoToa,txtGaDi,txtGaDen,txtThanhToan;
	JButton btnLuu;
	Connection conn=QuanLiVeTau.conn;
	PreparedStatement prepareStatement=QuanLiVeTau.prepareStatement;
	Statement sttm=QuanLiVeTau.sttm;
	public String MaGhe="";
	
	public static int ketqua=-1;
	
	public ClassEdit (String title)
	{
		this.setTitle(title);
		addControls();
		addEvents();
		if(MaGhe.length()!=0 )
		{
			hienThiThongTinChiTiet1();
		}
		
	}
	public void hienThiThongTinChiTiet1() {
		
		try 
		{
			
			String sql="SELECT vetau.MaGhe,khachhang.MaKH,khachhang.TenKH,khachhang.CMND,khachhang.SDT,vetau.TenTau,vetau.SoToa,vetau.GaDi,vetau.GaDen,vetau.ThanhToan FROM khachhang INNER JOIN vetau ON khachhang.MaKH=vetau.MaKH WHERE MaGhe='"+MaGhe+"' ";
			prepareStatement=conn.prepareStatement(sql);
			ResultSet result=prepareStatement.executeQuery();			
			if(result.next())
			{
				txtMaGhe.setText(result.getString(1));
				txtMaKH.setText(result.getString(2));
				txtTenKH.setText(result.getString(3));
				txtCMND.setText(result.getString(4));
				txtSDT.setText(result.getString(5));
				txtTenTau.setText(result.getString(6));
				txtSoToa.setText(result.getString(7));
				txtGaDi.setText(result.getString(8));
				txtGaDen.setText(result.getString(9));
				txtThanhToan.setText(result.getString(10));
			}
			txtMaGhe.setEditable(false);
			txtMaKH.setEditable(false);
			
		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		
	}
	
	private void addEvents() {
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyLuu();
				
			}

			private void xuLyLuu() {
				if(MaGhe.length()==0)
				{
					try 
					{
						String sql="SELECT vetau.MaGhe,khachhang.MaKH,khachhang.TenKH,khachhang.CMND,khachhang.SDT,vetau.TenTau,vetau.SoToa,vetau.GaDi,vetau.GaDen,vetau.ThanhToan FROM khachhang INNER JOIN vetau ON khachhang.MaKH=vetau.MaKH ";
						ResultSet result=sttm.executeQuery(sql);
						if(result.next())
						{
						txtMaGhe.setText(result.getString(1));
						txtMaKH.setText(result.getString(2));
						txtTenKH.setText(result.getString(3));
						txtCMND.setText(result.getString(4));
						txtSDT.setText(result.getString(5));
						txtTenTau.setText(result.getString(6));
						txtSoToa.setText(result.getString(7));
						txtGaDi.setText(result.getString(8));
						txtGaDen.setText(result.getString(9));
						txtThanhToan.setText(result.getString(10));
						sttm=conn.createStatement();
						int x=sttm.executeUpdate(sql);
						
						ketqua = x;
						}
						
					} catch (SQLException e) 
					{
						
					}
				}
			else
			{
				try 
				{
					String sql="update khachhang set TenKH='"+txtTenKH.getText()+"',CMND='"+txtCMND.getText()+"',SDT='"+txtSDT.getText()+"' WHERE MaKH='"+txtMaKH.getText()+"'";
					String sql1="update vetau set MaKH=(select MaKH from khachhang where MaKH='"+txtMaKH.getText()+"'),TenTau='"+txtTenTau.getText()+"',SoToa='"+txtSoToa.getText()+"',GaDi='"+txtGaDi.getText()+"',GaDen='"+txtGaDen.getText()+"',ThanhToan='"+txtThanhToan.getText()+"' WHERE MaGhe='"+txtMaGhe.getText()+"'";
				sttm=conn.createStatement();
				int x=sttm.executeUpdate(sql);
				int y=sttm.executeUpdate(sql1);
				ketqua=x;
				
				dispose();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				}
			}
		});
		
		}
	private void addControls() {
		// TODO Auto-generated method stub
		Container con=getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		
		
		
		JPanel pnMaGhe=new JPanel();
		pnMaGhe.setLayout(new FlowLayout());
		JLabel lblMaGhe=new JLabel("Mã Ghế");
		lblMaGhe.setForeground(Color.BLUE);
		txtMaGhe=new JTextField(20);
		pnMaGhe.add(lblMaGhe);
		pnMaGhe.add(txtMaGhe);
		con.add(pnMaGhe);		
		
		JPanel pnMaKH=new JPanel();
		pnMaKH.setLayout(new FlowLayout());
		JLabel lblMaKH=new JLabel("Mã Khách Hàng");
		lblMaKH.setForeground(Color.BLUE);
		txtMaKH=new JTextField(20);
		pnMaKH.add(lblMaKH);
		pnMaKH.add(txtMaKH);
		con.add(pnMaKH);
		
		JPanel pnTenKH=new JPanel();
		pnTenKH.setLayout(new FlowLayout());
		JLabel lblTenKH=new JLabel("Tên Khách Hàng");
		lblTenKH.setForeground(Color.BLUE);
		txtTenKH=new JTextField(20);
		pnTenKH.add(lblTenKH);
		pnTenKH.add(txtTenKH);
		con.add(pnTenKH);
		
		JPanel pnCMND=new JPanel();
		pnCMND.setLayout(new FlowLayout());
		JLabel lblCMND=new JLabel("CMND");
		lblCMND.setForeground(Color.BLUE);
		txtCMND=new JTextField(20);
		pnCMND.add(lblCMND);
		pnCMND.add(txtCMND);
		con.add(pnCMND);
		
		JPanel pnSDT=new JPanel();
		pnSDT.setLayout(new FlowLayout());
		JLabel lblSDT=new JLabel("Phone");
		lblSDT.setForeground(Color.BLUE);
		txtSDT=new JTextField(20);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);
		con.add(pnSDT);
		
		JPanel pnTenTau=new JPanel();
		pnTenTau.setLayout(new FlowLayout());
		JLabel lblTenTau=new JLabel("Tên Tàu");
		lblTenTau.setForeground(Color.BLUE);
		txtTenTau=new JTextField(20);
		pnTenTau.add(lblTenTau);
		pnTenTau.add(txtTenTau);
		con.add(pnTenTau);
		
		JPanel pnSoToa=new JPanel();
		pnSoToa.setLayout(new FlowLayout());
		JLabel lblSoToa=new JLabel("Số Toa");
		lblSoToa.setForeground(Color.BLUE);
		txtSoToa=new JTextField(20);
		pnSoToa.add(lblSoToa);
		pnSoToa.add(txtSoToa);
		con.add(pnSoToa);
		
		JPanel pnGaDi=new JPanel();
		pnGaDi.setLayout(new FlowLayout());
		JLabel lblGaDi=new JLabel("Ga Đi");
		lblGaDi.setForeground(Color.BLUE);
		txtGaDi=new JTextField(20);
		pnGaDi.add(lblGaDi);
		pnGaDi.add(txtGaDi);
		con.add(pnGaDi);
		
		JPanel pnGaDen=new JPanel();
		pnGaDen.setLayout(new FlowLayout());
		JLabel lblGaDen=new JLabel("Ga Đến");
		lblGaDen.setForeground(Color.BLUE);
		txtGaDen=new JTextField(20);
		pnGaDen.add(lblGaDen);
		pnGaDen.add(txtGaDen);
		con.add(pnGaDen);
		
		
		
		JPanel pnThanhToan=new JPanel();
		pnThanhToan.setLayout(new FlowLayout());
		JLabel lblThanhToan=new JLabel("Thanh Toán");
		lblThanhToan.setForeground(Color.BLUE);
		txtThanhToan=new JTextField(20);
		pnThanhToan.add(lblThanhToan);
		pnThanhToan.add(txtThanhToan);
		con.add(pnThanhToan);
		
		lblMaGhe.setPreferredSize(lblTenKH.getPreferredSize());
		lblMaKH.setPreferredSize(lblTenKH.getPreferredSize());
		lblThanhToan.setPreferredSize(lblTenKH.getPreferredSize());
		lblCMND.setPreferredSize(lblTenKH.getPreferredSize());
		lblSDT.setPreferredSize(lblTenKH.getPreferredSize());
		lblTenTau.setPreferredSize(lblTenKH.getPreferredSize());
		lblSoToa.setPreferredSize(lblTenKH.getPreferredSize());
		lblGaDi.setPreferredSize(lblTenKH.getPreferredSize());
		lblGaDen.setPreferredSize(lblTenKH.getPreferredSize());
		
		
		JPanel pnButton=new JPanel();
		btnLuu=new JButton("Save");
		pnButton.add(btnLuu);
		con.add(pnButton);
		
		
	}
	public void showWindow()
	{
		this.setSize(500, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}

}
