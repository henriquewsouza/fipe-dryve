package com.dryve.fipedryve;

import com.dryve.fipedryve.entity.FipeRequestBody;
import com.dryve.fipedryve.entity.FipeResponse;
import com.dryve.fipedryve.entity.Marca;
import com.dryve.fipedryve.entity.Modelo;
import com.dryve.fipedryve.entity.Veiculo;
import com.dryve.fipedryve.repository.MarcaRepository;
import com.dryve.fipedryve.repository.VeiculoRepository;
import com.dryve.fipedryve.service.FipeService;
import com.dryve.fipedryve.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;
    @Mock
    private FipeService fipeService;
    @Mock
    private MarcaRepository marcaRepository;

    private VeiculoService veiculoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        veiculoService = new VeiculoService(veiculoRepository, fipeService, marcaRepository);
    }

    @Test
    public void saveVeiculo_ValidVeiculo_ShouldSaveAndReturnVeiculo() {
        // Arrange
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("XYZ-1234");
        veiculo.setAno(2020);

        Modelo modelo = new Modelo();
        modelo.setId(UUID.randomUUID());
        veiculo.setModelo(modelo);

        Marca marca = new Marca();
        marca.setId(UUID.randomUUID());
        modelo.setMarca(marca);

        FipeResponse fipeResponse = new FipeResponse();
        fipeResponse.setValor("1234.56");

        when(veiculoRepository.existsByPlaca(veiculo.getPlaca())).thenReturn(false);
        when(fipeService.consultaFipe(any(FipeRequestBody.class))).thenReturn(fipeResponse);
        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        // Act
        Veiculo savedVeiculo = veiculoService.saveVeiculo(veiculo);

        // Assert
        assertNotNull(savedVeiculo);
        assertEquals(veiculo.getPlaca(), savedVeiculo.getPlaca());
        assertEquals(veiculo.getAno(), savedVeiculo.getAno());
        assertEquals(veiculo.getModelo(), savedVeiculo.getModelo());
        assertEquals(veiculo.getPrecoFipe(), savedVeiculo.getPrecoFipe());
        assertNotNull(savedVeiculo.getDataCadastro());
        verify(veiculoRepository, times(1)).existsByPlaca(veiculo.getPlaca());
        verify(fipeService, times(1)).getTabelaReferencia();
        verify(fipeService, times(1)).consultaFipe(any(FipeRequestBody.class));
        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    public void saveVeiculo_DuplicatePlaca_ShouldThrowException() {
        // Arrange
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("XYZ-1234");

        when(veiculoRepository.existsByPlaca(veiculo.getPlaca())).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> veiculoService.saveVeiculo(veiculo));

        verify(veiculoRepository, times(1)).existsByPlaca(veiculo.getPlaca());
        verify(veiculoRepository, never()).save(veiculo);
    }

    @Test
    public void getVeiculoByPlaca_ExistingPlaca_ShouldReturnVeiculo() {
        // Arrange
        String placa = "XYZ-1234";
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(placa);

        when(veiculoRepository.findByPlaca(placa)).thenReturn(Optional.of(veiculo));

        // Act
        Veiculo foundVeiculo = veiculoService.getVeiculoByPlaca(placa);

        // Assert
        assertNotNull(foundVeiculo);
        assertEquals(placa, foundVeiculo.getPlaca());
        verify(veiculoRepository, times(1)).findByPlaca(placa);
    }

    @Test
    public void getVeiculoByPlaca_NonExistingPlaca_ShouldThrowException() {
        // Arrange
        String placa = "XYZ-1234";

        when(veiculoRepository.findByPlaca(placa)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> veiculoService.getVeiculoByPlaca(placa));

        verify(veiculoRepository, times(1)).findByPlaca(placa);
    }

    // Add more tests for other methods if needed

}
