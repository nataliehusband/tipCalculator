package tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class calculator 
{
	private final static NumberFormat currency = NumberFormat.getCurrencyInstance(); 
	private final static NumberFormat percent = NumberFormat.getPercentInstance(); 
	@FXML 
	private BigDecimal tipPercentage = new BigDecimal(0.15); 
	@FXML
	private TextField amountTextField; 
	@FXML
	private TextField totalTextField; 
	@FXML
	private Slider tipPercentageSlider; 
	@FXML
	private Label tipPercentageLabel; 
	@FXML 
	private TextField tipTextField; 
	
	@FXML
	private void calculateButtonPressed(ActionEvent event)
	{
		figureTotal(); 
	}
	
	public void Initialize()
	{
		currency.setRoundingMode(RoundingMode.HALF_UP);
		
		tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>()
				{
					@Override
					public void changed(ObservableValue<? extends Number> observable, 
							Number oldValue, Number newValue)
					{
						tipPercentage = BigDecimal.valueOf(newValue.intValue()/100.0); 
						tipPercentageLabel.setText(percent.format(tipPercentage)); 
						System.out.println("fml");
					}
				});
	}
	
	private void figureTotal()
	{
		try
		{
			BigDecimal amount = new BigDecimal(amountTextField.getText());
			BigDecimal tip = amount.multiply(tipPercentage); 
			BigDecimal total = amount.add(tip); 
			
			tipTextField.setText(currency.format(tip)); 
			
			totalTextField.setText(currency.format(total));
		}
		catch(NumberFormatException ex)
		{
			amountTextField.setText("Enter Amount, please");
			amountTextField.selectAll();
			amountTextField.requestFocus();
		}
	}
}
