package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.Comment;
import io.kang.dto.mysql.CommentDTO;
import io.kang.repository.mysql.CommentRepository;
import io.kang.service.domain_service.implement_object.CommentServiceImpl;
import io.kang.service.domain_service.interfaces.CommentService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CommentCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.CommentUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CommentDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CommentDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
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
public class CommentServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CommentService commentService = new CommentServiceImpl();

    @Mock
    private CommentRepository commentRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.commentService).build();
    }

    @Test
    public void comment_find_all_test(){
        when(commentRepository.findAll()).thenReturn(Arrays.asList(CommentUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(commentService.findAll(), Arrays.asList(CommentDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void comment_get_one_success_test(){
        when(commentRepository.getOne(1L)).thenReturn(CommentUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(commentService.getOne(1L), CommentDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_get_one_failure_test(){
        Assert.assertEquals(commentService.getOne(0L), null);
    }

    @Test
    public void comment_find_by_id_success_test(){
        when(commentRepository.findById(1L)).thenReturn(Optional.of(CommentUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(commentService.findById(1L), CommentDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_find_by_id_failure_test(){
        when(commentRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(commentService.findById(0L), null);
    }

    @Test
    public void comment_find_by_request_order_by_written_date_desc_test(){
        when(commentRepository.findByRequestOrderByWrittenDateDesc(RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Arrays.asList(CommentUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(commentService.findByRequestOrderByWrittenDateDesc(RequestDTOUpdateSingleton.INSTANCE.getInstance()), Arrays.asList(CommentDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void comment_find_by_user_id_order_by_written_date_desc_test(){
        when(commentRepository.findByUserIdOrderByWrittenDateDesc("COMMENT_USER_ID01")).thenReturn(Arrays.asList(CommentUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(commentService.findByUserIdOrderByWrittenDateDesc("COMMENT_USER_ID01"), Arrays.asList(CommentDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void comment_create_test(){
        Comment createComment = CommentCreateSingleton.INSTANCE.getInstance();
        Comment afterComment = CommentUpdateSingleton.INSTANCE.getInstance();
        when(commentRepository.save(createComment)).thenReturn(afterComment);

        CommentDTO commentDTO = commentService.create(CommentDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(commentDTO.getId() != null);

    }

    @Test
    public void comment_update_test(){
        Comment updateComment = CommentUpdateSingleton.INSTANCE.getInstance();
        when(commentRepository.save(updateComment)).thenReturn(updateComment);

        CommentDTO commentDTO = commentService.update(CommentDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(commentDTO, CommentDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void comment_delete_by_id_test(){
        doNothing().when(commentRepository).deleteById(1L);
        commentService.deleteById(1L);
    }

    @Test
    public void comment_exists_by_id_success_test(){
        when(commentRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(commentService.existsById(1L));
    }

    @Test
    public void comment_exists_by_id_failure_test(){
        when(commentRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(commentService.existsById(1L));
    }

    @Test
    public void comment_count_test(){
        when(commentRepository.count()).thenReturn(5L);
        Assert.assertEquals(commentService.count(), 5L);
    }

    @Test
    public void comment_count_by_request_test(){
        when(commentRepository.countByRequest(RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(5L);
        Assert.assertEquals(commentService.countByRequest(RequestDTOUpdateSingleton.INSTANCE.getInstance()), 5L);
    }
}
