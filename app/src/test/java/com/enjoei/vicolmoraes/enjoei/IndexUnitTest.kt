package com.enjoei.vicolmoraes.enjoei

import com.enjoei.vicolmoraes.enjoei.Model.FotoBO
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IndexUnitTest {
    @Mock
    private lateinit var fotoBO: FotoBO

    companion object {
        const val URL_ACEITAVEL = "http://res.cloudinary.com/demo/image/upload/c_fill,g_auto,w_150,h_200/sample.jpg"
    }

    @Before
    fun before() {
        fotoBO = FotoBO()
        fotoBO.crop = "fill"
        fotoBO.gravity = "auto"
        fotoBO.public_id = "sample"
    }

    @Test
    fun validarSenha() {
        Assert.assertEquals(URL_ACEITAVEL, fotoBO.gerarUrl())
    }

}
