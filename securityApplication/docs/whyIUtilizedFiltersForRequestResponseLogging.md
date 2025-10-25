# Understanding Spring Boot Filter Execution Flow (`OncePerRequestFilter`)

In a Spring Boot application, when you write a custom filter such as a `OncePerRequestFilter`, **all code after**  
`filterChain.doFilter(request, response)` **executes once the response is generated**,  
i.e. when the request has completed processing through the rest of the chain, including the `DispatcherServlet`  
and your controller logic.

---

## 1. Request Flow in a Spring MVC Application

Here’s the high-level lifecycle:

1. The **client** sends an HTTP request.
2. The **Servlet container** (e.g., Tomcat) receives it.
3. The request passes through the chain of **filters** registered in the container.
    - Each filter runs its `doFilter()` / `doFilterInternal()` method in sequence.
    - Each filter typically calls `filterChain.doFilter(request, response)` to pass control to the next filter.
4. Once all filters have run, the **DispatcherServlet** receives the request.
5. **DispatcherServlet**:
    - Finds the matching **Controller** via handler mappings.
    - Invokes the **Controller method**.
    - Receives the Controller’s return value (`ResponseEntity`, `ModelAndView`, etc.).
    - Uses the appropriate **HandlerAdapter** and **ViewResolver** to build the HTTP response.
6. The **response now flows back** through the same filter chain in *reverse order*.
7. After control returns from `filterChain.doFilter(request, response)`,  
   the filter’s remaining logic runs — this is the **post-processing phase**.

---

## 2. What Happens Around `filterChain.doFilter()`

When you call:

filterChain.doFilter(request, response);



This line hands off the request to:

- The **next filter** in the chain if there is one; or
- The **DispatcherServlet** if your filter was the last in the chain.

Spring then executes all the usual MVC steps:

- Routing the request
- Invoking the controller
- Generating and writing the response

Only **after** the response is written, the call stack **unwinds**, and your filter resumes execution
right after the call to `doFilter()`.

### Example:


@Override
protected void doFilterInternal(HttpServletRequest request,
HttpServletResponse response,
FilterChain filterChain)
throws ServletException, IOException {
// Pre-processing (before controller)
logRequestDetails(request);



// Hand control forward
filterChain.doFilter(request, response);

// Post-processing (after controller, before response leaves)
logResponseDetails(response);
}



---

## 3. Sequence and Timing of Events

| Phase | Component           | Description                                         |
|--------|---------------------|-----------------------------------------------------|
| 1 | Incoming Filter | Logs or manipulates the request (before controller) |
| 2 | Other Filters | Security, CORS, etc. are executed in order |
| 3 | DispatcherServlet | Dispatches to Controller |
| 4 | Controller | Executes business logic and returns response |
| 5 | DispatcherServlet | Converts response object to HTTP response |
| 6 | Filter Chain Returns | Code after `filterChain.doFilter()` executes |
| 7 | Outgoing Filter | Logs final response before sending to client |

At **Step 6**, you are on the *response leg* of the request–response cycle.  
That’s when your `ContentCachingResponseWrapper` has fully captured the response body — so it’s safe to log it.

---

## 4. Key Internal Detail

The **Servlet container** guarantees that `filterChain.doFilter()` blocks synchronously until:

- The controller has run.
- The DispatcherServlet has written the response output stream.
- The request has completed (unless it went into async mode).

Only then does control return to your filter, allowing post-processing operations like:

- Logging the response
- Measuring execution time
- Cleaning up thread-local data

If you use **asynchronous requests** (`@Async`, `WebFlux`, or `DeferredResult`),  
the call might resume later in another thread. `OncePerRequestFilter` provides helper methods like  
`isAsyncDispatch()` and `isAsyncStarted()` to handle this safely.

---

## 5. Summary Analogy

Think of the filter as a wrapper around the entire request handling process:

|---> [Pre-handle code before chain.doFilter()]
|---> [Controller + business logic executes]
<---| [Post-handle code after chain.doFilter()]



So, to answer directly:

> Yes, in your logging filter, the code after  
> `filterChain.doFilter(request, response)`  
> runs **as the response is returned**, i.e. after your controller has processed the request and the response  
> is ready to be sent back through the filter chain.

---

### Related Topics

- How `OncePerRequestFilter` ensures single execution during forwards and includes
- What happens to filter invocation during async dispatch and callbacks
- How filter attributes mark a request as already filtered internally
- When filters run relative to DispatcherServlet and controller return flow
- Example sequence diagram showing `filter -> dispatcher -> controller -> response`




