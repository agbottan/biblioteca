package br.biblioteca.livros;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class livrosSeleniumTests {

	@Test
	public void deveCadastrarUsuarioTeste() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

		// Abre o navegador e entra na página de cadastro de usuários
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/registration");

		// Encontrando os campos na página
		WebElement elemNome				= driver.findElement(By.name("username"));
		WebElement elemPassword			= driver.findElement(By.name("password"));
		WebElement elemPasswordConfirm	= driver.findElement(By.name("passwordConfirm"));

		// Digitando os valores nos campos
		elemNome.sendKeys("Usuário de Teste");
		elemPassword.sendKeys("987654321");
		elemPasswordConfirm.sendKeys("987654321");

		// Clicando para enviar
		WebElement baoraoCadastrar = driver.findElement(By.name("cadastrar"));
		baoraoCadastrar.click();

        // Verificando se o novo usuário aparecem na listagem
        boolean achouNomeUsuario = driver.getPageSource().contains("Usuário de Teste");

        // Mostra pelo Junit
        assertTrue(achouNomeUsuario);

      	// Fecha o navegador do Selenium
      	driver.close();
    }
}
