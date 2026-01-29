let accessToken: string | null = null

export const authStore = {
  get: () => accessToken,
  set: (token: string) => (accessToken = token),
  clear: () => (accessToken = null),
}
