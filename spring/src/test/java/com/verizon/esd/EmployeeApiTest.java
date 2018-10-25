package com.verizon.esd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.convert.ThreeTenBackPortConverters.LocalDateTimeToJavaTimeInstantConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.verizon.esd.TestUtil.TestUtil;
import com.verizon.esd.model.Department;
import com.verizon.esd.model.Employee;
import com.verizon.esd.restapi.EmployeeApi;
import com.verizon.esd.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeApi.class)
public class EmployeeApiTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private EmployeeService empServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee());

		when(empServiceMock.getAllEmployees()).thenReturn(empList);

		mockMvc.perform(get("/Employees")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetEmployeeById() throws Exception {
		assertThat(this.empServiceMock).isNotNull();
		int empId = 13;
	

		Employee empAdded = new Employee();

		empAdded.setEmpId(13);
		empAdded.setEmpName("Aditya");
		empAdded.setBasic(8977);
		empAdded.setHra(45);
		empAdded.setDateOfJoining(LocalDate.of(2018, 10, 17));
		empAdded.setDept(Department.HR);

		when(empServiceMock.getEmployeeById(empId)).thenReturn(empAdded);

		mockMvc.perform(get("/Employees/13")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetAllEmployeess() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee());

		when(empServiceMock.getAllEmployees()).thenReturn(empList);

		mockMvc.perform(get("/Employees/empName/Aditya")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testAddEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee();

		emp.setEmpName("Raima");
		emp.setBasic(8977);
		emp.setHra(45);
		emp.setDateOfJoining(null);
		emp.setDept(Department.HR);

		Employee empAdded = new Employee();
		empAdded.setEmpId(14);
		empAdded.setEmpName("Raima");
		empAdded.setBasic(8977);
		empAdded.setHra(45);
		empAdded.setDateOfJoining(null);
		empAdded.setDept(Department.HR);

		System.out.println(emp);

		when(empServiceMock.addEmployee(Mockito.any(Employee.class))).thenReturn(empAdded);

		mockMvc.perform(post("/Employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(emp))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testUpdateEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee();
		emp.setEmpId(13);
		emp.setEmpName("RaimaBaby");
		emp.setBasic(8977);
		emp.setHra(45);
		emp.setDateOfJoining(null);
		emp.setDept(Department.Network);

		int empId = 13;
		

		Employee empAdded = new Employee();

		empAdded.setEmpId(13);
		empAdded.setEmpName("Aditya");
		empAdded.setBasic(8977);
		empAdded.setHra(45);
		empAdded.setDateOfJoining(LocalDate.of(2018, 10, 17));
		empAdded.setDept(Department.HR);

		when(empServiceMock.getEmployeeById(empId)).thenReturn(empAdded);

		when(empServiceMock.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp);

		mockMvc.perform(put("/Employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(emp))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));


	}

	@Test
	public void testDeleteEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		int empId = 10;

		when(empServiceMock.deleteEmployee(empId)).thenReturn(true);

		mockMvc.perform(delete("/Employees/10")).andExpect(status().isOk()).andDo(print());

	}

}
