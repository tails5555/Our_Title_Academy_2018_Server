package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.CommentEmpathy;
import io.kang.dto.mysql.CommentDTO;
import io.kang.dto.mysql.CommentEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.CommentEmpathyRepository;
import io.kang.service.domain_service.implement_object.CommentEmpathyServiceImpl;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CommentEmpathyCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.CommentEmpathyUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.CommentUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CommentDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CommentEmpathyDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CommentEmpathyDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
public class CommentEmpathyServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private EmpathyService<CommentEmpathyDTO, CommentDTO> commentEmpathyService = new CommentEmpathyServiceImpl();

    @Mock
    private CommentEmpathyRepository commentEmpathyRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.commentEmpathyService).build();
    }

    @Test
    public void comment_empathy_find_all_test(){
        when(commentEmpathyRepository.findAll()).thenReturn(Arrays.asList(CommentEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(commentEmpathyService.findAll(), Arrays.asList(CommentEmpathyDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void comment_empathy_get_one_success_test(){
        when(commentEmpathyRepository.getOne(1L)).thenReturn(CommentEmpathyUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(commentEmpathyService.getOne(1L), CommentEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_empathy_get_one_failure_test(){
        when(commentEmpathyRepository.getOne(0L)).thenReturn(null);
        Assert.assertEquals(commentEmpathyService.getOne(0L), null);
    }

    @Test
    public void comment_empathy_find_by_id_success_test(){
        when(commentEmpathyRepository.findById(1L)).thenReturn(Optional.of(CommentEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(commentEmpathyService.findById(1L), CommentEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_empathy_find_by_id_failure_test(){
        when(commentEmpathyRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(commentEmpathyService.findById(0L), null);
    }

    @Test
    public void comment_empathy_find_by_user_id_and_context_success_test(){
        when(commentEmpathyRepository.findByUserIdAndComment("EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.of(CommentEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(commentEmpathyService.findByUserIdAndContext("EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance()), CommentEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_empathy_find_by_user_id_and_context_failure_test(){
        when(commentEmpathyRepository.findByUserIdAndComment("EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.empty());
        Assert.assertEquals(commentEmpathyService.findByUserIdAndContext("EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance()), null);
    }

    @Test
    public void comment_empathy_create_test(){
        CommentEmpathy createCommentEmpathy = CommentEmpathyCreateSingleton.INSTANCE.getInstance();
        CommentEmpathy afterCommentEmpathy = CommentEmpathyUpdateSingleton.INSTANCE.getInstance();
        when(commentEmpathyRepository.save(createCommentEmpathy)).thenReturn(afterCommentEmpathy);

        CommentEmpathyDTO commentEmpathyDTO = commentEmpathyService.create(CommentEmpathyDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(commentEmpathyDTO.getId() != null);
    }

    @Test
    public void comment_empathy_update_test(){
        CommentEmpathy updateCommentEmpathy = CommentEmpathyUpdateSingleton.INSTANCE.getInstance();
        when(commentEmpathyRepository.save(updateCommentEmpathy)).thenReturn(updateCommentEmpathy);

        CommentEmpathyDTO commentEmpathyDTO = commentEmpathyService.update(CommentEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(commentEmpathyDTO, CommentEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_empathy_delete_by_id_test(){
        doNothing().when(commentEmpathyRepository).deleteById(1L);
        commentEmpathyService.deleteById(1L);
    }

    @Test
    public void comment_empathy_delete_by_user_id_and_context_test(){
        doNothing().when(commentEmpathyRepository).deleteByUserIdAndComment("EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance());
        commentEmpathyService.deleteByUserIdAndContext("EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_empathy_exists_by_id_success_test(){
        when(commentEmpathyRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(commentEmpathyService.existsById(1L));
    }

    @Test
    public void comment_empathy_exists_by_id_failure_test(){
        when(commentEmpathyRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(commentEmpathyService.existsById(1L));
    }

    @Test
    public void comment_empathy_exists_by_user_id_and_context_success_test(){
        when(commentEmpathyRepository.existsByUserIdAndComment("EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance())).thenReturn(true);
        Assert.assertTrue(commentEmpathyService.existsByUserIdAndContext("EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void comment_empathy_exists_by_user_id_and_context_failure_test(){
        when(commentEmpathyRepository.existsByUserIdAndComment("EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance())).thenReturn(false);
        Assert.assertFalse(commentEmpathyService.existsByUserIdAndContext("EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void comment_empathy_exists_by_user_id_and_context_and_status_success_test(){
        when(commentEmpathyRepository.existsByUserIdAndCommentAndStatus("EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(true);
        Assert.assertTrue(commentEmpathyService.existsByUserIdAndContextAndStatus("EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE));
    }

    @Test
    public void comment_empathy_exists_by_user_id_and_context_and_status_failure_test(){
        when(commentEmpathyRepository.existsByUserIdAndCommentAndStatus("EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(false);
        Assert.assertFalse(commentEmpathyService.existsByUserIdAndContextAndStatus("EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE));
    }

    @Test
    public void comment_empathy_count_test(){
        when(commentEmpathyRepository.count()).thenReturn(5L);
        Assert.assertEquals(commentEmpathyService.count(), 5L);
    }

    @Test
    public void comment_empathy_count_by_context_and_status_test(){
        when(commentEmpathyRepository.countByCommentAndStatus(CommentUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(5L);
        Assert.assertEquals(commentEmpathyService.countByContextAndStatus(CommentDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE), 5L);
    }
}
