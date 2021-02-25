package trankhanhduy.pro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.jdbc.Driver;;

public class QuanLiVeTau extends JFrame {
	JTextField txtMaGiuong,txtMaKH,txtTenKH,txtCMND,txtSDT,txtTenTau,txtSoToa,txtGaDi,txtGaDen,txtThanhToan,txtTimKiem;
	DefaultTableModel dtmVeTau;
	JTable tblVeTau;
	JButton btnAdd,btnEdit,btnDelete,btnSeach,btnExit,btnReset;
	JMenuItem mnuEdit,mnuDelete;
	JPopupMenu popupMenu;
	public static Connection conn=null;
	public static PreparedStatement prepareStatement=null;
	public static Statement sttm=null;
	
	
	public QuanLiVeTau (String title)
	{
		super(title);
		ketNoiMySQL();
		addControls();
		addEvents();
		hienThiThongTinChiTiet();
		
	}

	private void hienThiThongTinChiTiet() {
		try 
		{
			
			String sql="SELECT vetau.MaGhe,khachhang.MaKH,khachhang.TenKH,khachhang.CMND,khachhang.SDT,vetau.TenTau,vetau.SoToa,vetau.GaDi,vetau.GaDen,vetau.ThanhToan FROM khachhang INNER JOIN vetau ON khachhang.MaKH=vetau.MaKH ";
			prepareStatement=conn.prepareStatement(sql);
			ResultSet result=prepareStatement.executeQuery();			
			dtmVeTau.setRowCount(0);		
			while(result.next())
			{
				Vector<Object>vec=new Vector<Object>();
				vec.add(result.getString("MaGhe"));
				vec.add(result.getString("MaKH"));
				vec.add(result.getString("TenKH"));
				vec.add(result.getString("CMND"));
				vec.add(result.getString("SDT"));
				vec.add(result.getString("TenTau"));
				vec.add(result.getString("SoToa"));
				vec.add(result.getString("GaDi"));
				vec.add(result.getString("GaDen"));
				vec.add(result.getString("ThanhToan"));
				dtmVeTau.addRow(vec);
			}
		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		
	}

	private void addEvents() 
	{
		tblVeTau.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaGiuong.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 0).toString());
				txtMaKH.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 1).toString());
				txtTenKH.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 2).toString());
				txtCMND.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 3).toString());
				txtSDT.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 4).toString());
				txtTenTau.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 5).toString());
				txtSoToa.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 6).toString());
				txtGaDi.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 7).toString());
				txtGaDen.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 8).toString());
				txtThanhToan.setText(tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 9).toString());
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
				
					String sql2="insert into khachhang(MaKH,TenKH,CMND,SDT) values ('"+txtMaKH.getText()+"','"+txtTenKH.getText()+"','"+txtCMND.getText()+"','"+txtSDT.getText()+"')";
					String sql3="insert into vetau(MaGhe,MaKH,TenTau,SoToa,GaDi,GaDen,ThanhToan) values('"+txtMaGiuong.getText()+"',"
							+ "(select MaKH from khachhang where MaKH='"+txtMaKH.getText()+"'),"
									+ "'"+txtTenTau.getText()+"','"+txtSoToa.getText()+"',"
											+ "'"+txtGaDi.getText()+"','"+txtGaDen.getText()+"',"
													+ "'"+txtThanhToan.getText()+"')";
					sttm=conn.createStatement();
					int x=sttm.executeUpdate(sql2);					
					int y=sttm.executeUpdate(sql3);
					if(x>0 && y >0)
					{
						JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
						hienThiThongTinChiTiet();
					}
				} 
				catch (SQLException e2) 
				{
					JOptionPane.showMessageDialog(null, "Trùng Mã Giường Hoặc Mã Khách Hàng Vui Lòng Nhập Lại!");
				e2.printStackTrace();
				}
			}
			
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=tblVeTau.getSelectedRow();
				if(row==-1)
					return;
				String ma=tblVeTau.getValueAt(row, 0)+""; // lấy được dòng chọn

				ClassEdit open=new ClassEdit("Edit");
				
				open.MaGhe=ma;
				open.hienThiThongTinChiTiet1();
				open.showWindow();
				if(ClassEdit.ketqua>0 )
				{
					hienThiThongTinChiTiet();
					JOptionPane.showMessageDialog(null, "Cập Nhật Thành Công!");
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ma=tblVeTau.getValueAt(tblVeTau.getSelectedRow(), 1)+"";
				try 
				{
					String sql="delete from khachhang WHERE MaKH='"+ma+"'";
					sttm=conn.createStatement();
					int x=sttm.executeUpdate(sql);
					if(x>0)
					{
							JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
							hienThiThongTinChiTiet();					}
				} 
				catch (SQLException e2) 
				{
					e2.printStackTrace();
				}
				
			}
		});
		btnSeach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyTimKiem();				
			}

			private void xuLyTimKiem() {
				
				try 
				{
					String sql="SELECT vetau.MaGhe,khachhang.MaKH,khachhang.TenKH,khachhang.CMND,khachhang.SDT,vetau.TenTau,vetau.SoToa,vetau.GaDi,vetau.GaDen,vetau.ThanhToan FROM khachhang INNER JOIN vetau ON khachhang.MaKH=vetau.MaKH WHERE khachhang.MaKH LIKE '"+txtTimKiem.getText().trim()+"%'";
					 sttm=conn.createStatement();
		            ResultSet rs= sttm.executeQuery(sql);
		            dtmVeTau.getDataVector().removeAllElements();
		            while(rs.next())
		            {
		            	Vector<Object>row=new Vector<Object>();
		            	row.add(rs.getString("MaGhe"));
						row.add(rs.getString("MaKH"));
						row.add(rs.getString("TenKH"));
						row.add(rs.getString("CMND"));
						row.add(rs.getString("SDT"));
						row.add(rs.getString("TenTau"));
						row.add(rs.getString("SoToa"));
						row.add(rs.getString("GaDi"));
						row.add(rs.getString("GaDen"));
						row.add(rs.getString("ThanhToan"));
						dtmVeTau.addRow(row);
					}
		           
				} catch (SQLException e) 
				{
					e.printStackTrace();
					
				}
			}
		});
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int x=JOptionPane.showConfirmDialog(null, "Bạn Có Thật Sự Muốn Thoát!","Thoát",JOptionPane.YES_NO_OPTION);
				if(x==JOptionPane.NO_OPTION) return;
				if(x==JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null, "Cảm Ơn Bạn Đã Sử Dụng Phần Mềm!");
					System.exit(0);
				}
				
			}
		});
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					
					String sql="SELECT vetau.MaGhe,khachhang.MaKH,khachhang.TenKH,khachhang.CMND,khachhang.SDT,vetau.TenTau,vetau.SoToa,vetau.GaDi,vetau.GaDen,vetau.ThanhToan FROM khachhang INNER JOIN vetau ON khachhang.MaKH=vetau.MaKH ";
					prepareStatement=conn.prepareStatement(sql);
					ResultSet result=prepareStatement.executeQuery();			
					dtmVeTau.setRowCount(0);		
					while(result.next())
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(result.getString("MaGhe"));
						vec.add(result.getString("MaKH"));
						vec.add(result.getString("TenKH"));
						vec.add(result.getString("CMND"));
						vec.add(result.getString("SDT"));
						vec.add(result.getString("TenTau"));
						vec.add(result.getString("SoToa"));
						vec.add(result.getString("GaDi"));
						vec.add(result.getString("GaDen"));
						vec.add(result.getString("ThanhToan"));
						dtmVeTau.addRow(vec);
					}
				} 
				catch (SQLException ex) 
				{
					ex.printStackTrace();
				}
			}
		});

		tblVeTau.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger())
				{
					int row= tblVeTau.rowAtPoint(e.getPoint());
					int column=tblVeTau.columnAtPoint(e.getPoint());
					
					if (! tblVeTau.isRowSelected(row))
						tblVeTau.changeSelection(row, column, false, false);
					
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		mnuEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEdit.doClick();
				
			}
		});
		mnuDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnDelete.doClick();
				
			}
		});
	}

	private void addControls() 
	{
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth= new JPanel();
		JPanel pnCenter=new JPanel();
		JPanel pnSouth=new JPanel();
		con.add(pnNorth,BorderLayout.NORTH);
		con.add(pnCenter,BorderLayout.CENTER);
		con.add(pnSouth,BorderLayout.SOUTH);
		
		pnNorth.setLayout(new BorderLayout());
		JPanel pnTitle =new JPanel();
		JLabel lblTitle=new JLabel("Chương Trình Quản Lí Vé Tàu");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("SVN-Whimsy",Font.BOLD,20));
		pnTitle.add(lblTitle);
		pnNorth.add(pnTitle,BorderLayout.CENTER);
		
		
		pnCenter.setLayout(new BorderLayout());
		JPanel pnChiTiet=new JPanel();
		JPanel pnDanhSach=new JPanel();
		pnCenter.add(pnChiTiet,BorderLayout.WEST);
		pnCenter.add(pnDanhSach,BorderLayout.CENTER);
		
		pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
	
		JPanel pnMaGiuong =new JPanel();
		pnMaGiuong.setLayout(new FlowLayout());
		JLabel lblMaGiuong=new JLabel("Mã Giường");
		lblMaGiuong.setForeground(Color.BLUE);		
		txtMaGiuong=new JTextField(20);
		
		pnMaGiuong.add(lblMaGiuong);
		pnMaGiuong.add(txtMaGiuong);
		pnChiTiet.add(pnMaGiuong);
		
		JPanel pnMaKH =new JPanel();
		pnMaKH.setLayout(new FlowLayout());
		JLabel lblMaKH=new JLabel("Mã Khách Hàng");
		lblMaKH.setForeground(Color.BLUE);
		txtMaKH=new JTextField(20);
	
		pnMaKH.add(lblMaKH);
		pnMaKH.add(txtMaKH);
		pnChiTiet.add(pnMaKH);
		
		JPanel pnTenKH =new JPanel();
		pnTenKH.setLayout(new FlowLayout());
		JLabel lblTenKH=new JLabel("Tên Khách Hàng");
		lblTenKH.setForeground(Color.BLUE);
		txtTenKH=new JTextField(20);
		
		pnTenKH.add(lblTenKH);
		pnTenKH.add(txtTenKH);
		pnChiTiet.add(pnTenKH);
		
		JPanel pnCMND =new JPanel();
		pnCMND.setLayout(new FlowLayout());
		JLabel lblCMND=new JLabel("CMND");
		lblCMND.setForeground(Color.BLUE);
		txtCMND=new JTextField(20);
		
		pnCMND.add(lblCMND);
		pnCMND.add(txtCMND);
		pnChiTiet.add(pnCMND);		
		JPanel pnSDT =new JPanel();
		pnSDT.setLayout(new FlowLayout());
		JLabel lblSDT=new JLabel("Phone");
		lblSDT.setForeground(Color.BLUE);
		txtSDT=new JTextField(20);
		
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);
		pnChiTiet.add(pnSDT);
		
		JPanel pnTenTau =new JPanel();
		pnTenTau.setLayout(new FlowLayout());
		JLabel lblTenTau=new JLabel("Tên Tàu");
		lblTenTau.setForeground(Color.BLUE);		
		txtTenTau=new JTextField(20);
	
		pnTenTau.add(lblTenTau);
		pnTenTau.add(txtTenTau);
		pnChiTiet.add(pnTenTau);
		
		JPanel pnSoToa =new JPanel();
		pnSoToa.setLayout(new FlowLayout());
		JLabel lblSoToa=new JLabel("Số Toa");
		lblSoToa.setForeground(Color.BLUE);
		txtSoToa=new JTextField(20);
	
		pnSoToa.add(lblSoToa);
		pnSoToa.add(txtSoToa);
		pnChiTiet.add(pnSoToa);
		
		JPanel pnGaDi =new JPanel();
		pnGaDi.setLayout(new FlowLayout());
		JLabel lblGaDi=new JLabel("Ga Đi");
		lblGaDi.setForeground(Color.BLUE);
		txtGaDi=new JTextField(20);
	
		pnGaDi.add(lblGaDi);
		pnGaDi.add(txtGaDi);
		pnChiTiet.add(pnGaDi);
		
		JPanel pnGaDen =new JPanel();
		pnGaDen.setLayout(new FlowLayout());
		JLabel lblGaDen=new JLabel("Ga Đến");
		lblGaDen.setForeground(Color.BLUE);
		txtGaDen=new JTextField(20);
	
		pnGaDen.add(lblGaDen);
		pnGaDen.add(txtGaDen);
		pnChiTiet.add(pnGaDen);
		
		JPanel pnThanhToan =new JPanel();
		pnThanhToan.setLayout(new FlowLayout());
		JLabel lblThanhToan=new JLabel("Thanh Toán");
		lblThanhToan.setForeground(Color.BLUE);
		txtThanhToan=new JTextField(20);
	
		pnThanhToan.add(lblThanhToan);
		pnThanhToan.add(txtThanhToan);
		pnChiTiet.add(pnThanhToan);
		
		lblMaGiuong.setPreferredSize(lblTenKH.getPreferredSize());
		lblMaKH.setPreferredSize(lblTenKH.getPreferredSize());
		lblCMND.setPreferredSize(lblMaKH.getPreferredSize());
		lblSDT.setPreferredSize(lblMaKH.getPreferredSize());
		lblTenTau.setPreferredSize(lblMaKH.getPreferredSize());
		lblSoToa.setPreferredSize(lblMaKH.getPreferredSize());
		lblGaDi.setPreferredSize(lblMaKH.getPreferredSize());
		lblGaDen.setPreferredSize(lblMaKH.getPreferredSize());
		lblThanhToan.setPreferredSize(lblMaKH.getPreferredSize());
		
		Border border=BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder borderTitle=BorderFactory.createTitledBorder(border,"Chi Tiết");
		borderTitle.setTitleColor(Color.RED);
		pnChiTiet.setBorder(borderTitle);
		
		pnDanhSach.setLayout(new BorderLayout());
		
		dtmVeTau=new DefaultTableModel();
		dtmVeTau.addColumn("Mã Giường");
		dtmVeTau.addColumn("Mã KH");
		dtmVeTau.addColumn("Tên KH");
		dtmVeTau.addColumn("CMND");
		dtmVeTau.addColumn("SDT");
		dtmVeTau.addColumn("Tên Tàu");
		dtmVeTau.addColumn("Số Toa");
		dtmVeTau.addColumn("Ga Đi");
		dtmVeTau.addColumn("Ga Đến");
		dtmVeTau.addColumn("Thanh Toán");
		tblVeTau=new JTable(dtmVeTau);
		tblVeTau.setBackground(Color.WHITE);
		JScrollPane scTable=new JScrollPane(tblVeTau, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnDanhSach.add(scTable,BorderLayout.CENTER);
		
		Border borderDanhSach=BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleDanhSach=BorderFactory.createTitledBorder(borderDanhSach,"Danh Sách");
		titleDanhSach.setTitleColor(Color.RED);
		pnDanhSach.setBorder(titleDanhSach);
		
		pnSouth.setLayout(new BorderLayout());
		
		JPanel pnButton=new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnAdd=new JButton("Thêm Vé");
		btnEdit=new JButton("Sửa Vé");
		btnDelete=new JButton("Hủy Vé");
		btnReset=new JButton("Refresh");
		btnExit=new JButton("Exit");
		pnButton.add(btnAdd);
		pnButton.add(btnEdit);
		pnButton.add(btnDelete);
		pnButton.add(btnReset);
		pnButton.add(btnExit);
		pnSouth.add(pnButton,BorderLayout.WEST);
		
		JPanel pnTimKiem=new JPanel();
		pnTimKiem.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblTimKiem=new JLabel("Tìm Theo Mã Khách Hàng:");
		lblTimKiem.setForeground(Color.RED);
		txtTimKiem=new JTextField(20);
		btnSeach=new JButton("Search");
		pnTimKiem.add(lblTimKiem);
		pnTimKiem.add(txtTimKiem);
		pnTimKiem.add(btnSeach);
		pnSouth.add(pnTimKiem,BorderLayout.EAST);
		
		Border borderButton=BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder borderButtonTitle=BorderFactory.createTitledBorder(borderButton,"Chức Năng");
		borderButtonTitle.setTitleColor(Color.RED);
		pnSouth.setBorder(borderButtonTitle);
		
		btnAdd.setIcon(new ImageIcon("images/add.png"));
		btnEdit.setIcon(new ImageIcon("images/edit.png"));
		btnDelete.setIcon(new ImageIcon("images/delete.png"));
		btnReset.setIcon(new ImageIcon("images/reset.png"));
		btnSeach.setIcon(new ImageIcon("images/seach.png"));
		btnExit.setIcon(new ImageIcon("images/exit.png"));
		
		popupMenu=new JPopupMenu();
		mnuEdit=new JMenuItem("Edit");
		mnuDelete=new JMenuItem("Delete");
		popupMenu.add(mnuEdit);
		popupMenu.addSeparator();
		popupMenu.add(mnuDelete);
	}

	private void ketNoiMySQL() 
	{
		try 
		{
			String strlConn="jdbc:mysql://localhost/dbvetau";
			Properties pro=new Properties();
			pro.put("user", "root");
			pro.put("password", "");
			Driver driver=new Driver();
			conn=driver.connect(strlConn, pro);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	public void showWindow()
	{
		this.setSize(1250, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
